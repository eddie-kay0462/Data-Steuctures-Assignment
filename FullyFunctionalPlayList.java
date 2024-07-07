public class FullyFunctionalPlayList extends EnhancedPlayList{
    public FullyFunctionalPlayList(){
        super();
    }
    
    
    //method to toggle between continuous play and normal play modes
    public void toggleContinuousPlayMode() 
    {
        continuousPlayMode = !continuousPlayMode;
        System.out.println("Continuous play mode is now " + (continuousPlayMode ? "enabled" : "disabled"));
    }
}