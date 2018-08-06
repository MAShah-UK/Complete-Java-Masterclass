import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
}
