package rest.samples.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

@Path("/file")
public class FileUploadService {

	@Context
	HttpServletRequest request;

	@GET
	@Path("/download")
	@Produces("image/png")
	public Response downloadFile() {
		File file = new File(request.getRealPath("") + "/images/webservice.png");
		return Response.status(200).entity(file).header("Content-Disposition", "attachment; filename=webservice.png")
				.build();
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(List<Attachment> attachments) {
		for (Attachment attachment : attachments) {
			DataHandler handler = attachment.getDataHandler();
			try {
				InputStream in = handler.getInputStream();
				MultivaluedMap<String, String> map = attachment.getHeaders();
				String filename = getFileName(map);
				System.out.println("fileName Here:" + request.getRealPath("") + "/upload/" + filename);
				OutputStream out = new FileOutputStream(new File(request.getRealPath("") + "/upload/" + filename));

				int read = 0;
				byte[] buffer = new byte[1024];
				while ((read = in.read(buffer)) != -1) {
					out.write(buffer, 0, read);
				}
				in.close();
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Response.ok("file uploaded").build();
	}

	// preuzimanje naziva fajla koji je prosledjen u obliku:
	// Content-Disposition: form-data; name="file"; filename="datoteka.txt"
	private String getFileName(MultivaluedMap<String, String> header) {
		String[] parameters = header.getFirst("Content-Disposition").split(";");
		for (String parameter : parameters) {
			if ((parameter.trim().startsWith("filename"))) {
				String[] pair = parameter.split("=");
				String filename = pair[1].trim().replaceAll("\"", "");
				return filename;
			}
		}
		return "unknown";
	}

}