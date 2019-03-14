package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Song;
import model.SoundFile;
import services.SoundFileDao;
import util.JsonConverter;


@WebServlet(name = "SoundFileServlet", urlPatterns = {"/SoundFileServlet"})
public class SoundFileServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        JsonConverter<SoundFile> converter = new JsonConverter<SoundFile>();
        
        SoundFileDao dao = SoundFileDao.getInstance();
        List<SoundFile> files;
        
        String song = request.getParameter("song");
        if(song != null) {
            files = dao.getBySong(Integer.parseInt(song));
        } else {
            files = null;
        }
        String output = converter.convertToJson(files);
        out.print(output);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
