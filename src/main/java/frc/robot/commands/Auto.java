package frc.robot.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.structures.AutoCommand;
import frc.robot.subsystems.BallStopSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.NavigationSub;

public class Auto extends CommandBase {
  private static final Constants constants = Constants.getInstance();
  private final DriveSub drive;
  private final BallStopSub ballStop;
  private final IntakeSub intake;
  private final NavigationSub navigation;

  private List<AutoCommand> commands = new ArrayList<>();

  public Auto(DriveSub drive, BallStopSub ballStop, IntakeSub intake, NavigationSub navigation) {
    this.drive = drive;
    this.ballStop = ballStop;
    this.intake = intake;
    this.navigation = navigation;
    addRequirements(drive, ballStop, intake, navigation);
  }

  @Override
  public void initialize() {
    try (BufferedReader br = new BufferedReader(new FileReader(constants.autoCommandsPath))) {
      String line;

      while ((line = br.readLine()) != null) {
        commands.add(new AutoCommand(line));
      }

      commands.forEach(command -> {
        switch (command.name) {
          case "driveTo":
            break;
          case "driveBackTo":
            break;
          case "intakeRun":
            break;
          case "storageRun":
            break;
          default:
            break;
        }
      });

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void execute() {
    // TODO: Implement this
  }

  @Override
  public boolean isFinished() {
    // TODO: Implement this
    return false;
  }
}
