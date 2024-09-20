package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.FormDAO;
import model.Form;
import spark.Request;
import spark.Response;

 
public class FormService {

	private FormDAO formDAO = new FormDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID_FORM = 1;
	private final int FORM_ORDERBY_STATUS = 2;
	private final int FORM_ORDERBY_ID_ANIMAL = 3;
	
	
	public FormService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Form(), FORM_ORDERBY_ID_FORM);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Form(), orderBy);
	}

	
	public void makeForm(int tipo, Form produto, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umProduto = "";
		if(tipo != FORM_INSERT) {
			umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/produto/list/1\">Novo Form</a></b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>";
			umProduto += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT) {
			try{
				Scanner entrada = new Scanner(new File("formulario.html"));
			    while(entrada.hasNext()){
			    	umProduto += (entrada.nextLine() + "\n");
			    }
			    entrada.close();
			}  catch (Exception e) { System.out.println(e.getMessage()); }
		}
		
		else if(tipo == FORM_UPDATE) {
			String action = "/produto/";
			String name, moradia, buttonLabel;
			action += "update/" + produto.getIdForm();
			name = "Atualizar Form (ID " + produto.getIdForm() + ")";
			moradia = produto.getMoradia();
			buttonLabel = "Atualizar";
			umProduto += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;Moradia: <input class=\"input--register\" type=\"text\" name=\"f_residencia\" value=\""+ moradia +"\"></td>";
			umProduto += "\t\t\t<td>Experiência: <input class=\"input--register\" type=\"text\" name=\"f_exp\" value=\""+produto.getExp()+"\"></td>";
			umProduto += "\t\t\t<td>Receberia Visitas da ong: <input class=\"input--register\" type=\"text\" name=\"f_visita_ong\" value=\""+ produto.getVisitas() +"\"></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;Disponibilidade: <input class=\"input--register\" type=\"text\" name=\"f_tempo\" value=\""+ produto.getDisponibilidade() + "\"></td>";
			umProduto += "\t\t\t<td>Em caso de viagem, onde o animal ficaria: <input class=\"input--register\" type=\"text\" name=\"f_viagem\" value=\""+ produto.getViagem() + "\"></td>";
			umProduto += "\t\t\t<td>Comentarios: <input class=\"input--register\" type=\"text\" name=\"f_comentarios\" value=\""+ produto.getComentarios() + "\"></td>";
			umProduto += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>";
			umProduto += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Form (ID " + produto.getIdForm() + ")</b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;Moradia: "+ produto.getMoradia() +"</td>";
			umProduto += "\t\t\t<td>Experiencia: "+ produto.getExp() +"</td>";
			umProduto += "\t\t\t<td>Visitas Ong: "+ produto.getVisitas() +"</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;Disponibilidade: "+ produto.getDisponibilidade().toString() + "</td>";
			umProduto += "\t\t\t<td>Viagem: "+ produto.getViagem()+ "</td>";
			umProduto += "\t\t\t<td>Comentarios: "+ produto.getComentarios()+ "</td>";
			umProduto += "\t\t\t<td>&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>";	
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-PRODUTO>", umProduto);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Produtos</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_ID_FORM   + "\"><b>Id_form</b></a></td>\n" +
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_STATUS    + "\"><b>Status</b></a></td>\n" +
        		"\t<td><a href=\"/produto/list/" + FORM_ORDERBY_ID_ANIMAL + "\"><b>Id_animal</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Form> formularios;
		if (orderBy == FORM_ORDERBY_ID_FORM) {                 	formularios = formDAO.getOrderByID();
		} else if (orderBy == FORM_ORDERBY_STATUS) {			formularios = formDAO.getOrderByStatus();
		} else if (orderBy == FORM_ORDERBY_ID_ANIMAL) {			formularios = formDAO.getOrderByIdAnimal();
		} else {												formularios = formDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Form p : formularios) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getIdForm() + "</td>\n" +
            		  "\t<td>" + p.getStatus() + "</td>\n" +
            		  "\t<td>" + p.getIdAnimal() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/" + p.getIdForm() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/produto/update/" + p.getIdForm() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteProduto('" + p.getIdForm() + "', '" + p.getStatus() + "', '" + p.getIdAnimal() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-PRODUTO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		int id_a = 1;
		int id_u = 1;
		int this_status = 2;
		boolean visitas = Boolean.parseBoolean(request.queryParams("f_visita_ong"));
		boolean exp = Boolean.parseBoolean(request.queryParams("f_exp"));
		String moradia = request.queryParams("f_residencia");
		String disponibilidade = request.queryParams("f_tempo");
		String viagem = request.queryParams("f_viagem");
		String comentarios = request.queryParams("f_comentarios");
	
		
		String resp = "";
		
		Form f = new Form(-1,id_a,id_u,this_status,visitas,exp,moradia,disponibilidade,viagem,comentarios);

		if(formDAO.insert(f) == true) {
            resp = "Form (" + comentarios + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Form (" + comentarios + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Form produto = (Form) formDAO.get(id);
		
		if (produto != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, produto, FORM_ORDERBY_STATUS);
        } else {
            response.status(404); // 404 Not found
            String resp = "Form " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));		
		Form produto = (Form) formDAO.get(id);
		
		if (produto != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, produto, FORM_ORDERBY_STATUS);
        } else {
            response.status(404); // 404 Not found
            String resp = "Form " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Form produto = formDAO.get(id);
        String resp = "";       

        if (produto != null) {
        	produto.setVisitas(Boolean.parseBoolean(request.queryParams("f_visita_ong")));
        	produto.setExp(Boolean.parseBoolean(request.queryParams("f_exp")));
    		produto.setMoradia(request.queryParams("f_residencia"));
    		produto.setDisponibilidade(request.queryParams("f_tempo"));
    		produto.setViagem(request.queryParams("f_viagem"));
    		produto.setComentarios(request.queryParams("f_comentarios"));
    		
        	formDAO.update(produto);
        	response.status(200); // success
            resp = "Form (ID " + produto.getIdForm() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Form (ID \" + produto.getId() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Form produto = formDAO.get(id);
        String resp = "";       

        if (produto != null) {
            formDAO.delete(id);
            response.status(200); // success
            resp = "Form (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Form (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}