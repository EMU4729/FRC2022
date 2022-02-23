package frc.robot.Utility;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Date;
import java.util.Calendar;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import edu.wpi.first.wpilibj.RobotController;

import frc.robot.Constants;

public class WriteLog {
  private static Optional<WriteLog> log = Optional.empty();
  private ArrayList<String> toLog = new ArrayList<String>();
  private final String logFileName;
  private final Constants consts = Constants.getInstance();
  private boolean fileCreationFailed = false;

  private WriteLog(){
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
    String strDate = dateFormat.format(date);

    String tempLogName = consts.FilePath+strDate+".txt";
    System.out.println(tempLogName);
    File logFile = new File(tempLogName);
    try {
      for(int i = 1; !logFile.createNewFile(); i++){
        if(i>10){
          fileCreationFailed = true; 
          System.out.println("error : Log file creation failed : time out");
          break;
        }
        tempLogName = consts.FilePath+strDate+"_("+i+")"+".txt";
        logFile = new File(tempLogName);
      }
    } catch (IOException e){
      System.out.println("error : Log file creation failed : "+e.toString());
    }
    logFileName = tempLogName;
  }
  public static WriteLog getInstance(){
    if(!log.isPresent()){
      log = Optional.of(new WriteLog());
    }
    return log.get();
  }

  public static void LogString(String log){
    WriteLog workingLog = getInstance();
    workingLog.toLog.add(log);
  }

  public void logList(){
    if(toLog.isEmpty() || fileCreationFailed){
      return;
    }
    try{
      FileWriter logWritter = new FileWriter(logFileName, true);
      while(!toLog.isEmpty()){
        logWritter.write(getgameTime()+" "+toLog.get(0));
        logWritter.write(System.getProperty("line.separator"));
        toLog.remove(0);
      }
      logWritter.close();
    } catch(IOException e){
      System.out.println("error : write to log file failed : "+ e.toString());
    }
  }

  private String getgameTime(){
    return String.valueOf((int)(RobotController.getFPGATime()));
  }

}
