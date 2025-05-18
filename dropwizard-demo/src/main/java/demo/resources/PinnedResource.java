package demo.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class PinnedResource {

  @GET
  public String pinned() {
    return "pinned";
  }

  @GET
  @Path("/test")
  public String getTestData() {
    Thread.ofVirtual().start(() -> {
      doPinned();
    });

    return "hello";
  }

  private void doPinned() {
    System.out.println("Im pin doPinned method!");
    synchronized (this) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        System.out.println("pinned ended!");
      }
    }
  }
}
