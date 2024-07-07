//Class representing a fully functional playlist that extends the Enhanced playlist
public class FullyFunctionalPlayList extends EnhancedPlayList{

    //Constructor to initialise a fully functional playlist
    public FullyFunctionalPlayList(){
        super(); //Call t he constructor the superclass Enhaced playlist
    }
    
    
    //method to toggle between continuous play and normal play modes
    public void toggleContinuousPlayMode() 
    {
        continuousPlayMode = !continuousPlayMode; //toggle the continuous playmode boolean variable
        System.out.println("Continuous play mode is now " + (continuousPlayMode ? "enabled" : "disabled")); //print the current mode
    }
}