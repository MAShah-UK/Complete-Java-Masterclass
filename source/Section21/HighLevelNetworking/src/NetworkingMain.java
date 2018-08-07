import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class NetworkingMain {
    public static void main(String[] args) {
        NetworkingMain main = new NetworkingMain();
        // Exception: Invalid syntax.
        main.printURIComponents("hello");
        // Works: Valid URI, but invalid resource since it doesn't exist.
        main.printURIComponents("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
        // Exception: db is an unknown scheme.
        main.URItoURL("db://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
        // Works: Valid URL.
        main.URItoURL("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
        // Exception: Can't convert relative URI to URL which is absolute.
        main.URItoURL("/catalogue/phones?os=android#samsung");

        main.resolveURI("http://username:password@myserver.com:5000",
                "/catalogue/phones?os=android#samsung");
        main.relativizeURI("http://username:password@myserver.com:5000",
                "http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");
        main.readURL("http://example.org");
    }
    // Practice working with URIs.
    public void printURIComponents(String uriString) {
        System.out.println("\nBEGIN: printURIComponents for " + uriString);
        try {
            URI uri = new URI(uriString);
            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Scheme-specific part: " + uri.getSchemeSpecificPart());
            System.out.println("Authority: " + uri.getAuthority());
            System.out.println("User-info: " + uri.getUserInfo());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Port: " + uri.getPort());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
            System.out.println("Fragment: " + uri.getFragment());
        } catch(URISyntaxException e) {
            System.out.println("URISyntaxException: " + e.getMessage());
        }
    }
    // Practice converting URI to URL.
    public void URItoURL(String uriString) {
        System.out.println("\nBEGIN: URItoURL for " + uriString);
        try {
            URL url = new URI(uriString).toURL();
            System.out.println("URL is: " + url);
        } catch(MalformedURLException e) {
            System.out.println("MalformedURLException: " + e.getMessage());
        } catch(URISyntaxException e) {
            System.out.println("URISyntaxException: " + e.getMessage());
        } catch(IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }
    }
    // Practice resolving/combining URIs.
    public void resolveURI(String base, String specific) {
        System.out.println("\nBEGIN: resolveURI");
        try {
            URI baseURI = new URI(base);
            URI specificURI = new URI(specific);
            URI resolvedURI = baseURI.resolve(specificURI);
            System.out.println("Resolved URI: " + resolvedURI);
        } catch(URISyntaxException e) {
            System.out.println("URISyntaxException: " + e.getMessage());
        }
    }
    // Practice extracting the relative URI.
    public void relativizeURI(String base, String absolute) {
        System.out.println("\nBEGIN: relativizeURI");
        try {
            URI baseURI = new URI(base);
            URI absoluteURI = new URI(absolute);
            URI relativizedURI = baseURI.relativize(absoluteURI);
            System.out.println("Relativized URI: " + relativizedURI);
        } catch(URISyntaxException e) {
            System.out.println("URISyntaxException: " + e.getMessage());
        }
    }
    // Practice reading from an URL.
    public void readURL(String urlString) {
        System.out.println("\nBEGIN: readURL target " + urlString);
        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            while(reader.ready()) {
                System.out.println(reader.readLine());
            }
            reader.close();
            /*
            URL.openStream() is a shortcut for:
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true); // Configure, then connect.
            connection.connect();
            connection.getInputStream(); // BufferedReader...
             */
        } catch(MalformedURLException e) {
            System.out.println("MalformedURLException: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
