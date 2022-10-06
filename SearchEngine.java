import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList<String> strings = new ArrayList<String>();
    
    public String handleRequest(URI url) {
        String returnString = "";
        if(url.getPath().equals("/")) {
            for(int i = 0; i < strings.size(); i ++) {
                returnString += strings.get(i) + " ";
            }

            return returnString;
        }

        else if(url.getPath().equals("/search")) {
            String returnString2 = "";
            ArrayList<String> queryStrings = new ArrayList<String>();
            String[] querySearch = url.getQuery().split("=");

            if(querySearch[0].equals("s")) {
                String substring = querySearch[1];
                for(int i = 0; i < strings.size(); i++) {
                    if(strings.get(i).contains(substring)) {
                        returnString2 += strings.get(i) + " ";
                    }
                }
            }

            return returnString2;
        }

        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] querySearch = url.getQuery().split("=");
                if (querySearch[0].equals("s")) {
                    String stringToAdd = querySearch[1];
                    strings.add(stringToAdd);
                    return("String added!");
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
