import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yoav tamir
 * @version 1.3
 *
 * this test is build for the following gui dimensions:
 * BouncingBallAnimation:
 * width = 200;F
 * height = 200;
 *
 * MultipleBouncingBallsAnimation:
 * width = 450;
 * height = 450;
 *
 * MultipleFramesBouncingBallsAnimation
 * width = 650;
 * height = 650;
 * big frame = 450/450
 * small frame = 150/150
 *
 * you can change this dimensions in the start of every test
 *
 * you need to insert this:
 * -------------------------------------
  // from here - just for check!!!!
  // replace gui - name of your gui
  if (gui.getKeyboardSensor().isPressed("space")) {
  return;
  }


  // to here - just for check!!!
 * ----------------------------------------
 * -> in all of the loops that activate the animations.
 * with that-> you can break to loop by press "space" button.
 * <p>
 * dont forgot to remove it!!
 */
public class BallsTester {
    public static void main(String[] args) {
        GUI guiT = new GUI("Balls Test ", 400, 400);
        DrawSurface d = guiT.getDrawSurface();

        //########################### first test####################

        Ball b1 = new Ball(200, 200, 200, java.awt.Color.RED);
        Ball b2 = new Ball(200, 200, 300, java.awt.Color.BLUE);
        Ball b3 = new Ball(-80, 249, 50, java.awt.Color.GREEN);
        Ball b4 = new Ball(80, 249, 50, java.awt.Color.GREEN);
        Ball b5 = new Ball(80, 249, 20, Color.black);
        Ball b6 = new Ball(200, 200, 0, java.awt.Color.RED);


        guiT.getDialogManager().showConfirmationDialog("Tests 1", "test Ball class");

        guiT.getDialogManager().showInformationDialog("check1", "2 balls in the same center");
        d = guiT.getDrawSurface();
        b4.drawOn(d);
        b5.drawOn(d);
        guiT.show(d);
        d = guiT.getDrawSurface();

        guiT.getDialogManager().showInformationDialog("check2", "ball has radius 0");
        b6.drawOn(d);
        guiT.show(d);
        d = guiT.getDrawSurface();
        //########################### sec test####################

        int width = 200;
        int height = 200;
        int halfWidth = (int) width / 2;
        int halfHeight = (int) height / 2;

        guiT.show(d);
        guiT.getDialogManager().showConfirmationDialog("Tests 2", "test BouncingBallAnimation class\n press SPACE to skip animation");

        guiT.getDialogManager().showInformationDialog("check1", "ball center- (0,0)");
        String[] args1 = new String[]{"0", "0", "5", "2"};
        BouncingBallAnimation.main(args1);

        guiT.getDialogManager().showInformationDialog("check2", "ball center- (1,1)");
        String[] args2 = new String[]{"1", "1", "5", "2"};
        BouncingBallAnimation.main(args2);

        guiT.getDialogManager().showInformationDialog("check3", "ball center- ("+width+","+height+")");
        String[] args3 = new String[]{String.valueOf(width), String.valueOf(height), "5", "2"};
        BouncingBallAnimation.main(args3);

        guiT.getDialogManager().showInformationDialog("check4", "ball center- ("+(width-1)+",0)");
        String[] args4 = new String[]{String.valueOf(height - 1), "0", "5", "2"};
        BouncingBallAnimation.main(args4);

        guiT.getDialogManager().showInformationDialog("check5", "ball center- (-50,10)");
        String[] args5 = new String[]{"-50", "10", "5", "2"};
        BouncingBallAnimation.main(args5);

//        guiT.getDialogManager().showInformationDialog("check6", "not numeric input - \"b b z %\"");
//        String[] args6 = new String[]{"b", "b", "z", "%"};
//        BouncingBallAnimation.main(args6);




        //########################### third test####################
        width = 450;
        height = 450;
        halfWidth = (int) width / 2;
        halfHeight = (int) height / 2;
        int maxRadius = Math.min(halfHeight, halfWidth);

        d = guiT.getDrawSurface();
        guiT.show(d);
        guiT.getDialogManager().showConfirmationDialog("Tests 3", "test MultipleBouncingBallsAnimation class\n press SPACE to skip animation");

        String[] argsT = new String[]{"0", "0", "65", "12"};
        String title = "radius is zero\n";
        String strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check1", strT);
        MultipleBouncingBallsAnimation.main(argsT);

        argsT = new String[]{String.valueOf(maxRadius), "12", "50", "100"};
        title = "ball is in limit size\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check2", strT);
        MultipleBouncingBallsAnimation.main(argsT);

        argsT = new String[]{String.valueOf(maxRadius - 1), "12", "50", "100"};
        title = "ball is in limit size-2\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check3", strT);
        MultipleBouncingBallsAnimation.main(argsT);

        argsT = new String[]{String.valueOf(maxRadius + 10), "350", "50", "100"};
        title = "ball is bigger than the limit size\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check4", strT);
        MultipleBouncingBallsAnimation.main(argsT);

        argsT = new String[100];
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            argsT[i] = String.valueOf(rand.nextInt(maxRadius-50 )); // generate random x value.
        }
        title = "tons of balls\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check5", strT);
        MultipleBouncingBallsAnimation.main(argsT);

        argsT = new String[]{"35", "-5", "50", "100"};
        title = "negative number input\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check6", strT);
        MultipleBouncingBallsAnimation.main(argsT);

//        argsT = new String[]{"b", "b", "z", "%"};
//        title = "not numeric input";
//        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
//        guiT.getDialogManager().showInformationDialog("check7", strT);
//        MultipleBouncingBallsAnimation.main(argsT);

        //###########################forth test####################
        int smallFrameWidth = 150;
        int smallFrameHeight = 150;
        int smallFrameHalfWidth = (int) smallFrameWidth / 2;
        int smallFrameHalfHeight = (int) smallFrameHeight / 2;
        int smallFrameMaxRadius = Math.min(smallFrameHalfWidth, smallFrameHalfHeight);

        int bigFrameWidth = 450;
        int bigFrameHeight = 450;
        int bigFrameHalfWidth = (int) bigFrameWidth / 2;
        int bigFrameHalfHeight = (int) bigFrameHeight / 2;
        int bigFrameMaxRadius = Math.min(bigFrameHalfWidth, bigFrameHalfHeight);

        guiT.getDialogManager().showConfirmationDialog("Tests 4",
                "test MultipleFramesBouncingBallsAnimation class\n press SPACE to skip animation");

        argsT = new String[]{"0", "0", "65", "12"};
        title = "radius is zero\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check1", strT);
        MultipleFramesBouncingBallsAnimation.main(argsT);

        argsT = new String[]{String.valueOf(bigFrameMaxRadius), "12", "50", String.valueOf(smallFrameMaxRadius)};
        title = "ball is in limit size\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check2", strT);
        MultipleFramesBouncingBallsAnimation.main(argsT);

        argsT = new String[]{String.valueOf(bigFrameMaxRadius - 1), "12", "50", String.valueOf(smallFrameMaxRadius - 1)};
        title = "ball is in limit size-2\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check3", strT);
        MultipleFramesBouncingBallsAnimation.main(argsT);

        argsT = new String[]{String.valueOf(bigFrameMaxRadius + 10), "350", "50", String.valueOf(smallFrameMaxRadius + 10)};
        title = "ball is bigger than the limit size\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check4", strT);
        MultipleFramesBouncingBallsAnimation.main(argsT);

        argsT = new String[]{"35", "-5", "50", "-100"};
        title = "negative number input\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check5", strT);
        MultipleBouncingBallsAnimation.main(argsT);
//
//        argsT = new String[]{"b", "b", "z", "%"};
//        title = "not numeric input";
//        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
//        guiT.getDialogManager().showInformationDialog("check6", strT);
//        MultipleBouncingBallsAnimation.main(argsT);

        // new test
        guiT.getDialogManager().showConfirmationDialog("Tests 4", "more test then previous ");

        argsT = new String[]{String.valueOf(smallFrameMaxRadius + 2), "200", "50", "20"};
        title = "the balls of the big frame to big for the other frame\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check7", strT);
        MultipleFramesBouncingBallsAnimation.main(argsT);

        argsT = new String[]{"50", "50", "50", "50"};
        title = "every ball in the same size\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check8", strT);
        MultipleFramesBouncingBallsAnimation.main(argsT);

        argsT = new String[100];
        for (int i = 0; i < 100; i++) {
            argsT[i] = String.valueOf(rand.nextInt(smallFrameMaxRadius)); // generate random x value.
        }
        title = "tons of balls\n";
        strT = title + "The input is:\n" + Stream.of(argsT).collect(Collectors.joining(", "));
        guiT.getDialogManager().showInformationDialog("check9", strT);
        MultipleFramesBouncingBallsAnimation.main(argsT);
        guiT.getDialogManager().showInformationDialog("Finis", "this is the end!");
        guiT.close();

    }
}