package app;

import static spark.Spark.*;
import service.FormService;


public class Aplicacao {
	
	private static FormService formService = new FormService();
	
    public static void main(String[] args) {
        port(3111);
        
        staticFiles.location("/public");
        
        
        post("/produto/insert", (request, response) -> formService.insert(request, response));

        get("/produto/:id", (request, response) -> formService.get(request, response));
        
        get("/produto/list/:orderby", (request, response) -> formService.getAll(request, response));

        get("/produto/update/:id", (request, response) -> formService.getToUpdate(request, response));
        
        post("/produto/update/:id", (request, response) -> formService.update(request, response));
           
        get("/produto/delete/:id", (request, response) -> formService.delete(request, response));

          
    }
}