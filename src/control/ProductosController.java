/*
 * Copyright (C) 2015 Steven Rod.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package control;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.ModelProductos;

/**
 *
 * @author Steven
 */



public  class ProductosController implements Inter_Vista {

    
   
   private  Connect conexion;
   
   public ProductosController()  {
        conexion = new Connect();
    }
    
    /**
     * Vacia la tabla de Productos
     * @return
     */
    @Override
    public boolean dumpTable() {
      
           return  conexion.executeStatement("drop table if exists productos");
  
    }
    //====================================
    /**
     * Crea una nueva tabla Productos
     * @return boolean 
     */
    @Override
    public boolean createTableProductos() {
   
       try {
           conexion = new Connect();
           Class.forName("org.sqlite.JDBC");
           
           dumpTable(); // Drop al table si existe , xq la vamos a crear en este punto
           
           return conexion.executeStatement("create table productos ( "
                   + " idProducto        string, "
                   + " nombre            string,"
                   + " descripcion       string,"
                   + " cantidad          String,"
                   + " unidad_medida     string,"
                   //---------------------------
                   + " cantidadMinima    string,"
                   + " cantidadMaxima    string,"
                   + " usuario_registra     string,"
                   + " usuario_actualiza    string )");
       } catch (ClassNotFoundException ex) {
           return false;
       }
            
          
    }


//======================================================================================
    
    /**
     * Inserta un nuevo producto con caracteristicas extendidas
     * @param product
     * @return  boolean
     */
   @Override
    public boolean insertProduct(ModelProductos product) {
     
            //---------------------------------
            return conexion.executeStatement("insert into productos values(  "
                    + " '" + product.idProducto + "',"
                    + " '" + product.nombre + "',"
                    + " '" + product.descripcion + "',"
                    + " '" + product.cantidad + "',"
                    + " '" + product.unidad_medida + "',"
                    //---------------------------------
                    + " '" + product.cantidad_minima + "'," //-- cantidadMinima
                    + " '" + product.cantidad_maxima + "'," //-- cantidadMaxima
                   
                    + " '" + product.usuario_registra + "'," //-- userRegistra
                    + " '"+product.usuario_actualiza +"');"); //userActualiza     
      
    }

//======================================================================================
    /**
     * Obtiene todos los elementos de la tabla productos y retorna una lista
     *
     * @return ArrayList<ModelProductos> ;
     */
    @Override
    public ArrayList<ModelProductos> getAllProducts() {
        ArrayList<ModelProductos> miArray = new ArrayList<>();
        try {
            ResultSet rs = conexion.getStatement().executeQuery("select * from productos");
            while (rs.next()) {

                ModelProductos miPrduct = new ModelProductos();
                // read the result set
                try {
                    miPrduct.setIdProducto(rs.getString("idProducto"));
                    miPrduct.setNombre(rs.getString("nombre"));
                    miPrduct.setDescripcion(rs.getString("descripcion"));
                    miPrduct.setCantidad(rs.getString("cantidad"));
                    miPrduct.setUnidad_medida(rs.getString("unidad_medida"));
                    miPrduct.setCantidad_minima(rs.getString("cantidadMinima"));
                    miPrduct.setCantidad_maxima(rs.getString("cantidadMaxima"));
                 
                    miPrduct.setUsuario_registra(rs.getString("usuario_registra"));
                    miPrduct.setUsuario_actualiza(rs.getString("usuario_actualiza"));

                    miArray.add(miPrduct);

                } catch (Exception e) {
                  
                }
            }
        } catch (Exception e) {
         
        }
        return miArray;
    }

    public ArrayList<ModelProductos> getAllProductsBy(String x) {
        ArrayList<ModelProductos> miArray = new ArrayList<>();
        try {
            ResultSet rs = conexion.getStatement().executeQuery("select * from productos where nombre LIKE '%"+ x+"%';");
            while (rs.next()) {

                ModelProductos miPrduct = new ModelProductos();
                // read the result set
                try {
                    miPrduct.setIdProducto(rs.getString("idProducto"));
                    miPrduct.setNombre(rs.getString("nombre"));
                    miPrduct.setDescripcion(rs.getString("descripcion"));
                    miPrduct.setCantidad(rs.getString("cantidad"));
                    miPrduct.setUnidad_medida(rs.getString("unidad_medida"));
                    miPrduct.setCantidad_minima(rs.getString("cantidadMinima"));
                    miPrduct.setCantidad_maxima(rs.getString("cantidadMaxima"));
                 
                    miPrduct.setUsuario_registra(rs.getString("usuario_registra"));
                    miPrduct.setUsuario_actualiza(rs.getString("usuario_actualiza"));

                    miArray.add(miPrduct);

                } catch (Exception e) {
                  
                }
            }
        } catch (Exception e) {
         
        }
        return miArray;
    }
    @Override
    public boolean editCantidad(double cant, String id) {

        return conexion.executeStatement("update productos set cantidad = '" + cant + 
                                          "' where idProducto ='" + id + "'");
    }

//=====================================================================================    
    @Override
    public boolean delete(String id) {

        return conexion.executeStatement("delete from productos where idProducto= '" + id + "'");

    }

   

}
