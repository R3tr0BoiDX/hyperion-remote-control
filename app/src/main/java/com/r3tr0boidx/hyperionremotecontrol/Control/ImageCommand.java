package com.r3tr0boidx.hyperionremotecontrol.Control;

import android.util.Log;

import com.r3tr0boidx.hyperionremotecontrol.Networking.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageCommand implements ControlCommand {

    public static final String COMMAND = "image";
    public static final int RECOMMENDED_PRIORITY = 50;

    //required
    private final int priority;
    private final String imageData;

    //not required
    private Integer duration;
    private String origin;
    private Integer imageWidth;                     //Not mentioned in official documentation
    private Integer imageHeight;                    //Not mentioned in official documentation
    private FormatTypes format = FormatTypes.auto;  //Not sure, how to use this correctly, see https://doc.qt.io/qt-5/qimagereader.html#supportedImageFormats
    private Integer scale;                          //Not mentioned in official documentation
    private String name;

    /**
     * Create a new command, to set a image as source for the colors
     * The official Hyperion documentation recommends priorities between 2 and 99, optimally 50
     * @param priority The priority of the command. Recommended is 50, min. is 1, max. is 253
     * @param imageData The image, that is to use as source for the colors
     */
    public ImageCommand(int priority, String imageData) {
        if (priority > 0 && priority < 254) {
            this.priority = priority;
            this.imageData = imageData;
        } else {
            throw new IllegalArgumentException("Priority was to high or low. Min. is 1, max. is 253. Given priority: " + priority);
        }
    }

    @Override
    public Response execute() {
        return ControlCommand.super.execute();
    }

    @Override
    public JSONObject buildCommand() {
        try {
            JSONObject json = new JSONObject();
            json.put(ControlHelper.COMMAND_KEY, COMMAND);
            json.put("imagedata", imageData);
            json.put("priority", priority);

            if (duration != null) {
                json.put("duration", duration);
            }

            if (origin != null) {
                json.put("origin", origin);
            }

            if (imageWidth != null){
                json.put("imagewidth", imageWidth);
            }

            if (imageHeight != null){
                json.put("imageheight", imageHeight);
            }

            if (format != null){
                json.put("format", format.toString());
            }

            if (scale != null){
                json.put("scale", scale);
            }

            if (name != null){
                json.put("name", name);
            }

            return json;

        } catch (JSONException e) {
            Log.e("buildCommand", "Can't build " + COMMAND + " command");
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * Duration of color in ms. Indefinite by default
     *
     * @param duration The duration is ms
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * A short name of your application.
     *
     * @param origin Name of application, that send command. Max length is 20, min 4
     */
    public void setOrigin(String origin) {
        if (origin.length() > 3 && origin.length() < 21) {
            this.origin = origin;
        } else {
            throw new IllegalArgumentException("Name was either to short or to long. Min. 4, max. 20 characters. Given length: " + origin.length());
        }
    }

    /**
     * Set the width of the image, if known.
     * ATTENTION: Not mentioned in official documentation, might not work
     *
     * @param imageWidth The width of the image
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * Set the height of the image, if known.
     * ATTENTION: Not mentioned in official documentation, might not work
     *
     * @param imageHeight The height of the image
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * Set the format of the image. See https://doc.qt.io/qt-5/qimagereader.html#supportedImageFormats for more infos
     *
     * @param format A supported format file format. WIP right now
     */
    public void setFormat(FormatTypes format) {
        this.format = format;
    }

    /**
     * Set a specific scale leve
     * ATTENTION: Not mentioned in official documentation, might not work
     *
     * @param scale Scale image to the given scale. The ratio of the image is preserved. Must be between 25 and 2000
     */
    public void setScale(int scale) {
        if (scale < 2000 && scale > 25) {
            this.scale = scale;
        } else {
            throw new IllegalArgumentException("Scale must be between 25 and 2000. Given scale: " + scale);
        }
    }

    /**
     * The name of the image
     *
     * @param name The name that appears in the web interface
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Format, that can be used for the images.
     * See https://doc.qt.io/qt-5/qimagereader.html#supportedImageFormats for more informations
     * Use "auto", to have Hyperion figure type out
     */
    enum FormatTypes{
        bmp,
        gif,
        jpg,
        png,
        pbm,
        pgm,
        ppm,
        xbm,
        xpm,
        svg,
        auto //Hyperion will figure it out
    }

}
