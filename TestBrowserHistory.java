
public class TestBrowserHistory
{
    public static void main(String[] args) 
    {
        BrowserHistory browserHistory = new BrowserHistory();
        browserHistory.addPage("www.example1.com", "10:00AM");
        browserHistory.addPage("www.example2.com", "10:05AM");
        browserHistory.addPage("www.example3.com", "10:10AM");
        browserHistory.addPage("www.youtube.com", "12:00PM");
        browserHistory.removePage("10:05AM");
        browserHistory.removePage("12:00PM");
        
        browserHistory.displayHistoryForward();
        System.out.println();
        browserHistory.displayHistoryBackward();
        browserHistory.saveBrowserHistory();
        System.out.println(":::LOADING THE BROWSER HISTORY FROM THE FILE:::\n");
        browserHistory.loadBrowserHistory();
 
        



    }    



}
