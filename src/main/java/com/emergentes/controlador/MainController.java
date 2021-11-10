package com.emergentes.controlador;
import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op;
            op=(request.getParameter("op")!=null)? request.getParameter("op"):"list";
            ArrayList<Seminario> lista=new ArrayList<Seminario>();
            ConexionBD canal=new ConexionBD();
            Connection conn=canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (op.equals("list")) {
                String sql="SELECT * FROM seminarios";
                ps=conn.prepareStatement(sql);
                rs=ps.executeQuery();
                while(rs.next()){
                    Seminario semi=new Seminario();
                    semi.setId(rs.getInt("id"));
                    semi.setTitulo(rs.getString("titulo"));
                    semi.setExpositor(rs.getString("expositor"));
                    semi.setFecha(rs.getString("fecha"));
                    semi.setHora(rs.getString("hora"));
                    semi.setCupo(rs.getInt("cupo"));
                    lista.add(semi);
                }
                request.setAttribute("lista",lista);
                request.getRequestDispatcher("index.jsp").forward(request, response); 
            }
            if (op.equals("nuevo")) {
                Seminario s=new Seminario();
                System.out.println(s.toString());
                request.setAttribute("semi", s);
                request.getRequestDispatcher("editar.jsp").forward(request, response); 
            }
            if (op.equals("editar")) {
                int id=Integer.parseInt(request.getParameter("id"));
                Seminario semi=new Seminario();
                String sql="SELECT * FROM seminarios WHERE id=?";
                ps=conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs=ps.executeQuery();
                if(rs.next()){                 
                    semi.setId(rs.getInt("id"));
                    semi.setTitulo(rs.getString("titulo"));
                    semi.setExpositor(rs.getString("expositor"));
                    semi.setFecha(rs.getString("fecha"));
                    semi.setHora(rs.getString("hora"));
                    semi.setCupo(rs.getInt("cupo"));
                }
                request.setAttribute("semi",semi);
                request.getRequestDispatcher("editar.jsp").forward(request, response); 
            }
            if (op.equals("eliminar")) {
                int id=Integer.parseInt(request.getParameter("id"));
                String sql="DELETE FROM seminarios WHERE id=?";
                ps=conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR AL CONECTAR "+ex.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id=Integer.parseInt(request.getParameter("id"));
            System.out.println("Valor de ID "+id);
            String titulo=request.getParameter("titulo");
            String expositor=request.getParameter("expositor");
            String fecha=request.getParameter("fecha");
            String hora=request.getParameter("hora");
            int cupo=Integer.parseInt(request.getParameter("cupo"));
            Seminario semi=new Seminario();
            semi.setId(id);
            semi.setTitulo(titulo);
            semi.setExpositor(expositor);
            semi.setFecha(fecha);
            semi.setHora(hora);
            semi.setCupo(cupo);
            ConexionBD canal=new  ConexionBD();
            Connection conn=canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (id==0) {
                String sql="INSERT INTO seminarios(titulo,expositor,fecha,hora,cupo) VALUES(?,?,?,?,?)";
                ps=conn.prepareStatement(sql);
                ps.setString(1, semi.getTitulo());
                ps.setString(2, semi.getExpositor());
                ps.setString(3, semi.getFecha());
                ps.setString(4, semi.getHora());
                ps.setInt(5, semi.getCupo());
                ps.executeUpdate();
            }else{
                String sql="UPDATE seminarios SET titulo=?,expositor=?,fecha=?,hora=?,cupo=? WHERE id=?";
                ps=conn.prepareStatement(sql);
                ps.setString(1, semi.getTitulo());
                ps.setString(2, semi.getExpositor());
                ps.setString(3, semi.getFecha());
                ps.setString(4, semi.getHora());
                ps.setInt(5, semi.getCupo());
                ps.setInt(6, semi.getId());
                ps.executeUpdate();
            }
            response.sendRedirect("MainController");
        } catch (SQLException ex) {
            System.out.println("Error de SQL "+ex.getMessage());
        }
    }
}