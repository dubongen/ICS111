import java.awt.Color;
import java.util.Random;

public class Race {
	
	public static void main(String[] args) {

		// Setup EZ graphics system.
		EZ.initialize(1100,600); //Size of screen
		EZ.setBackgroundColor(new Color(0,0,0)); 
		
		EZImage Track = EZ.addImage("Race_track.png",550,300); //add image of track
		
		EZImage Flash = EZ.addImage("flash.png", 20, 140); //add racer image and resize photo
		Flash.scaleTo(.22f);
		
		EZImage Zoom = EZ.addImage("zoom.png", 20, 330);//add racer image and resize photo
		Zoom.scaleTo(.27f);
		
		EZImage ReverseFlash = EZ.addImage("reverse_flash.png", 20, 525);//add racer image and resize photo
		ReverseFlash.scaleTo(.10f);
		
		
		EZSound Flash_Theme = EZ.addSound("The_Flash_Theme.wav");//add flash theme sound
		EZSound FlashLead = EZ.addSound("FlashAhead.wav");//add sound
		EZSound FlashWins = EZ.addSound("FlashWins.wav");//add sound
		
		EZSound ReverseFlash_Theme = EZ.addSound("Reverse_Flash_Theme.wav");//add Reverse Flash sound
		EZSound ReverseLead = EZ.addSound("ReverseAhead.wav");//add sound
		EZSound ReverseWins = EZ.addSound("ReverseWins.wav");//add sound
		
		EZSound Zoom_Theme = EZ.addSound("FrankensteinsMonster.wav");//add Zoom sound
		EZSound ZoomLead = EZ.addSound("ZoomAhead.wav");//add sound
		EZSound ZoomWins = EZ.addSound("ZoomWins.wav");//add sound
		
		EZSound Tsunami = EZ.addSound("The_Tsunami.wav");//add background sound
	
		Tsunami.loop();//loop song
			
		  Color c = new Color(255, 255, 255);//add text color
		  int fontsize = 40;//text font size
		 
		 
			
		EZText text = EZ.addText(400, 50, "Get Ready!", c, fontsize);//add text
			EZ.pause(1000);//pause for 1 seconds
		
		EZSound Start = EZ.addSound("Start.wav");//add sound
				Start.play(); //play sound once
			
			EZ.pause(10000);//pause for 10 seconds
			
			text.setMsg("3");//add text
			EZSound three = EZ.addSound("3.wav");//add sound
			three.play();//play sound once
			EZ.pause(2000);//pause for 2 seconds
			
			text.setMsg("2");//add text
			EZSound two = EZ.addSound("2.wav");//add sound
			two.play();//play sound once
			EZ.pause(2000);//pause for 2 seconds
			
			text.setMsg("1");//add text	
			EZSound one = EZ.addSound("1.wav");//add sound
			one.play();//play sound once
			EZ.pause(2000);//pause for 2 seconds
			 
			text.setMsg("GO!");//add text
			EZSound GO = EZ.addSound("Go.wav");//add sound
			GO.play();//play sound once
			
			
		Random rand = new Random(); //create a variable that makes a random number
		int winm=1100/16; //make a win margin thats 1/16 of screen
		int reverseFlashX=40/2;//set X variable to 40/2
		int flashX=40/2;//set X variable to 40/2
		int zoomX=40/2;//set X variable to 40/2
		char lastkey='s';//set the last key pressed to S
		char currkey='a';//set the first key pressed to A
		char tem;//set it to be temporary
		
		int currwin=0;//set current winner to 0
		Boolean isracing=true;//create a boolean that makes isracing true
		
		Zoom.translateTo(zoomX,330);//translate the coordinates
		ReverseFlash.translateTo(reverseFlashX,525);//translate the coordinates	
		

		while (isracing){
			Zoom.translateBy (rand.nextInt(4),0); //random integer multiplied by x value of racer's coordinates
			
			ReverseFlash.translateBy (rand.nextInt(4),0); //random integer multiplied by x value of racer's coordinates
			
			EZ.refreshScreen(); //When screen refresh reset random integers
			
			
			Flash.translateTo(flashX,140);//translate the coordinates
			
			if (EZInteraction.wasKeyPressed(currkey)==true) { //set key interaction
				tem=lastkey;//temporary is the last key pressed
				lastkey=currkey;//the last key = the current key
				currkey=tem;//temporary is the current key pressed
				Flash.translateBy (5+rand.nextInt(8),0);	// when keys is pressed add 5 and a random int to flashX
			}	
		
			
		
		flashX=Flash.getXCenter();//get the X center of image
		zoomX=Zoom.getXCenter();//get the X center of image
		reverseFlashX=ReverseFlash.getXCenter();//get the X center of image
		
		if ((flashX - Math.max(zoomX, reverseFlashX) ) >= winm && currwin !=1){ //minus flashX to the current max of both X variables and has to be greater than winm and currwin doesn't = to 1 
			currwin = 1;//set currwin =1
			FlashLead.play();//play sound 
			text.setMsg("The Flash is in the lead!");//add text
			
			
		}else if ((flashX - Math.max(zoomX, reverseFlashX) ) < 0 && currwin == 1){//minus X to the current max of both X variables and has to be less than 0 and currwin = to 1
			text.setMsg("");//add text
			currwin=0;//set currwin =0
		}
		
		if ((zoomX - Math.max(flashX, reverseFlashX) ) >= winm && currwin !=2){//minus X to the current max of both X variables and has to be greater than winm and currwin doesn't = to 2
			text.setMsg("Zoom is in the lead!");//add text
			ZoomLead.play();//play sound 
			currwin=2;//set currwin =2
		
		}else if ((zoomX - Math.max(flashX, reverseFlashX) ) < 0 && currwin == 2){//minus X to the current max of both X variables and has to be less than 0 and currwin = to 2
			text.setMsg("");//add text
			currwin=1;//set currwin =1
		}
		
		if ((reverseFlashX - Math.max(flashX, zoomX) ) >= winm && currwin !=3){//minus X to the current max of both X variables and has to be greater than winm and currwin doesn't = to 3
			text.setMsg("Reverse Flash is in the lead!");//add text
			ReverseLead.play();//play sound 
			currwin=3;//set currwin =3
		
		}else if ((reverseFlashX - Math.max(flashX, zoomX) ) < 0 && currwin == 3){//minus X to the current max of both X variables and has to be less than 0 and currwin = to 3
			text.setMsg("");//add text
			currwin=2;//set currwin =2
		}
		
		if(flashX>=1060){
			text.setMsg("The Flash Wins!");//add text
			isracing = false;//make isracing false
			Tsunami.stop();//stop sound
			FlashWins.play();//play sound 
			Flash_Theme.loop();//loop sound
			
		}
		
		if(zoomX>=1060){//X variable is greater than or equal to 1060
		text.setMsg("Zoom Wins!");//add text
		isracing = false;//make isracing false
		Tsunami.stop();//stop sound
		ZoomWins.play();//play sound 
		Zoom_Theme.loop();//loop sound
		
		}
		
		if(reverseFlashX>=1060){//X variable is greater than or equal to 1060
		text.setMsg("Reverse Flash Wins!");//add text
		isracing = false;//make isracing false
		Tsunami.stop();//stop sound
		ReverseWins.play();//play sound 
		ReverseFlash_Theme.loop();//loop sound
		
		}
		if((flashX==1060) && (zoomX==1060) || (flashX==1060) && (reverseFlashX==1060) || (zoomX==1060) && (reverseFlashX==1060)){//if any 2 X variables = to 1060
		isracing = false;//make isracing false
		text.setMsg("It's a Tie!");//add text
		}
		
	}
  }
}	