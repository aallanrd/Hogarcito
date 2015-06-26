/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import model.ModelProductos;

/**
 *
 * @author Allan
 */
public interface Inter_Vista {
    
    public boolean dumpTable();
    public boolean createTableProductos();
    public boolean insertProduct(ModelProductos product);
    public ArrayList<ModelProductos> getAllProducts();
    public boolean editCantidad(double cant, String id);
    public boolean delete(String id);
    
}
