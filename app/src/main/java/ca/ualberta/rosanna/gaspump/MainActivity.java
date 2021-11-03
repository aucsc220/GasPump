/*
 * This program provides a simple simulation of a gas pump for an Android Phone.  This is
 * the starting java file.
 *
 * Created for an assignment in AUCSC 220.
 * by R. Heise
 * November 2021
 *
 * Usage:
 *   1) Open the app.
 *   2) Click on a type of gas.
 *   3) Move the seek bar to fill up the tank.
 *      - Number of litres and price display at the bottom.
 *   Notes:
 *   - User may move the seek bar backwards and user may change the
 *     type of gas as filling is happening.  Program assumes tank has
 *     only one kind of gas (not a mixture).
 *   - If user forgets to select a type of gas, user will be
 *     prompted with a Toast
 *
 */
package ca.ualberta.rosanna.gaspump;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Data - items needed to be shared in methods that don't allow parameters to be passed
    //       due to the strict format of the methods (e.g. onClicks)
    private boolean gasTypeSelected = false; //Becomes true once a gas type is selected
    private double costPerLitre = 0.0; //Nothing selected yet so don't know cost at start
    private double progressOfFill = 0.0; //Used to track progress especially to give access
                                         //when gas type not yet selected (i.e. in button
                                         //methods)

    /**
     * When program starts, set up the seek bar for filling the gas tank
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the seek bar that handles filling the gas tank
        SeekBar fillTankSB = (SeekBar) findViewById(R.id.tankFillingSeekBar);
        fillTankSB.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    /**
                     * Displays litres (and price - if gas type is known) on screen.
                     * @param seekBar the gas filling up bar
                     * @param progress how far along (out of 1000)
                     * @param fromUser true, as user is the only control expected
                     * Sideeffect: Changes instance variable
                     */
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressOfFill = progress / 10; //In litres (tenths) now; using instance
                                                        //variable in case no gas type selected
                                                        //and want access to this number when
                                                        //user clicks a gas type button
                        if (gasTypeSelected) {
                            TextView actLitTV = (TextView) findViewById(R.id.actLitresTV);
                            TextView actCostTV = (TextView) findViewById(R.id.actCostTV);

                            actLitTV.setText(String.format("%6.1f", progressOfFill));
                            actCostTV.setText(String.format("%6.2f",
                                    (progressOfFill * costPerLitre / 100.0)));
                        }
                        else {
                            //empty for now
                        }

                    }//onProgressChanged

                    /**
                     * Displays toast if the gas type has not been selected.
                     * @param seekBar the gas filling up bar
                     */
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        if (!gasTypeSelected){ //Put toast here so it disappears faster
                            Toast.makeText(MainActivity.this, "Pick a type of gas",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //empty on purpose - this method is required for SeekBars
                    }
                }
        );//setOnSeekBarChangeListener
    }//onCreate

    /**
     * Sets up the instance data for regular gas; highlights button; updates litres and price.
     * @param regButtonV - the button the customer pressed
     * Side-effects: changes instance variables, and screen info
     */
    public void regGas(View regButtonV){
        //TODO The three gas types interact (future: consider radio button)
        //Find the 3 buttons
        Button regButton = (Button) regButtonV;
        Button midGradeButton = (Button) findViewById(R.id.midGradeButton);
        Button premiumButton = (Button) findViewById(R.id.premiumButton);

        //Highlight regular button
        regButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
        midGradeButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
        premiumButton.setBackgroundColor(getResources().getColor(R.color.purple_200));

        //Change controlling instance variables
        costPerLitre = Double.parseDouble(getResources().getString(R.string.regular_price));
        gasTypeSelected = true;

        //update litres and price
        TextView numLitresTV = (TextView) findViewById(R.id.actLitresTV);
        TextView actCostTV = (TextView) findViewById(R.id.actCostTV);
        numLitresTV.setText(String.format("%6.1f", progressOfFill));
        actCostTV.setText(String.format("%6.2f",
                (progressOfFill * costPerLitre / 100.0)));
    }//regGas

    /**
     * Sets up the instance data for mid-grade gas; highlights button; updates litres and price.
     * @param midGradeButtonV - the button the customer pressed
     * Side-effects: changes instance variables and screen info
     */
    public void midGradeGas(View midGradeButtonV){
        Button midButton = (Button) midGradeButtonV;
        Button regButton = (Button) findViewById(R.id.regularButton);
        Button premiumButton = (Button) findViewById(R.id.premiumButton);

        //Highlight mid grade button
        midButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
        regButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
        premiumButton.setBackgroundColor(getResources().getColor(R.color.purple_200));

        //Change controlling instance variables
        costPerLitre = Double.parseDouble(getResources().getString(R.string.mid_grad_price));
        gasTypeSelected = true;

        //update litres and price
        TextView numLitresTV = (TextView) findViewById(R.id.actLitresTV);
        TextView actCostTV = (TextView) findViewById(R.id.actCostTV);
        numLitresTV.setText(String.format("%6.1f", progressOfFill));
        actCostTV.setText(String.format("%6.2f",
                (progressOfFill * costPerLitre / 100.0)));
    }//midGradeGas

    /**
     * Sets up the instance data for premium gas; highlights button; updates litres and price.
     * @param premiumButtonV - the button the customer pressed
     * Side-effects: changes instance variables and screen info
     */
    public void premiumGas(View premiumButtonV){
        Button premiumButton = (Button) premiumButtonV;
        Button regButton = (Button) findViewById(R.id.regularButton);
        Button midGradeButton = (Button) findViewById(R.id.midGradeButton);

        //Highlight premium grade button
        premiumButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
        regButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
        midGradeButton.setBackgroundColor(getResources().getColor(R.color.purple_200));

        //Change controlling instance variables
        costPerLitre = Double.parseDouble(getResources().getString(R.string.premium_price));
        gasTypeSelected = true;

        //update litres and price
        TextView numLitresTV = (TextView) findViewById(R.id.actLitresTV);
        TextView actCostTV = (TextView) findViewById(R.id.actCostTV);
        numLitresTV.setText(String.format("%6.1f", progressOfFill));
        actCostTV.setText(String.format("%6.2f",
                (progressOfFill * costPerLitre / 100.0)));
    }//midGradeGas
}//end of class