package com.mohsin.pharmapedia.Helpers.Entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mohsin.pharmapedia.Models.ApiCall.apiCallDrugsResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    static Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    static Pattern HTML_PATTERN = Pattern.compile("<(\"[^\"]*\"|'[^']*'|[^'\">])*>");
    static Pattern VALID_MOBILE_NO_REGEX = Pattern.compile("^((\\+92)|(0092))-{0,1}\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$");
    static Pattern VALID_Password_Regular_Expression = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,14}$");


    static Pattern VALID_CNIC_Regular_Expression = Pattern.compile("^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$");

    //   static Pattern VALID_CNIC_Regular_Expression = Pattern.compile("^[0-9+]{5}[0-9+]{7}[0-9]{1}$");


    public static int getRandom6DigitNumber() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return Integer.parseInt(String.format("%06d", number));
    }

    public static String createMobileNoWith92(String mobileNo) {
        if (validateMobileNo(mobileNo)) {
            mobileNo = mobileNo.substring(1);
            mobileNo = "92" + mobileNo;
            return mobileNo;
        }
        return mobileNo;
    }

    public static Bitmap decodeBitmapFromFile(String path, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
//
//        Log.i(TAG, "decodeBitmapFromFile --- required size --- width:" + reqWidth + " --- height:" + reqHeight);
//        Log.i(TAG, "decodeBitmapFromFile --- query size --- width:" + options.outWidth + " --- height:" + options.outHeight);
//        Log.i(TAG, "decodeBitmapFromFile --- insample size = " + options.inSampleSize);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
//        try {
//          //  return rotateImageIfRequired(bitmap, path);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            //  AppLog.i(TAG, "calculateInSampleSize --- inSampleSize : " + inSampleSize);

            final int halfHeight = height;
            final int halfWidth = width;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
                //   AppLog.i(TAG, "calculateInSampleSize --- inSampleSize : " + inSampleSize);
                //  AppLog.i(TAG, "calculateInSampleSize --- calculating size --- width:" + halfWidth + " --- height:" + halfHeight);
            }
            //AppLog.i(TAG, "calculateInSampleSize --- final size --- width:" + halfWidth + " --- height:" + halfHeight);
        }
        //  AppLog.i(TAG, "calculateInSampleSize --- insample size = " + inSampleSize);
        return inSampleSize;
    }

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean isHTML(String html) {
        Matcher matcher = HTML_PATTERN.matcher(html);
        return matcher.find();
    }

    public static boolean validateCNIC(String cnic) {
        Matcher matcher = VALID_CNIC_Regular_Expression.matcher(cnic);
        return matcher.find();
    }

    public static boolean validateMobileNo(String mobileNo) {
        Matcher matcher = VALID_MOBILE_NO_REGEX.matcher(mobileNo);
        return matcher.find();
    }


    public static boolean validatePasswordRegularExpression(String passwordStr) {
        Matcher matcher = VALID_Password_Regular_Expression.matcher(passwordStr);
        return matcher.find();
    }

    public static String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }


    public static String HTMLToString(InputStream is) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(is, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }

    public static String getHTMLForm(Context currentActivity, String Form) throws IOException {
        InputStream masterjson = currentActivity.getResources().getAssets().open(Form);
        String masterJsonString = HTMLToString(masterjson);

        return masterJsonString;
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(32);
        char tempChar;
        for (int i = 0; i < 32; i++) {
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }


    public static String formatCNIC(String cnic) {

        String tmp = cnic.substring(0, 5) + "-" + cnic.substring(5, 12) + "-" + cnic.substring(12);


        return tmp;


    }

    public static Class getResponseClass(String type) {

//        ************************ response models *********************************
        if (type.equals(Constants.apiCallDrugs)) {
            return apiCallDrugsResponse.class;
        }
//
//        else if (type.equals(Constants.apiError)) {
//            return apiErrorResponse.class;
//        }
        return null;
    }


    public static String CreateJson(String keyword, String locName, String startDate, String endDate) {
        JSONObject obj = new JSONObject();
        try {
            if (keyword != "") {
                obj.put("keyword", keyword);
            }

            if (locName != "") {
                obj.put("locName", locName);
            }

            if (startDate != "") {
                obj.put("startDate", startDate);
            }

            if (endDate != "") {
                obj.put("endDate", endDate);
            }

            return new JSONObject().put("Advance", obj).toString();
        } catch (JSONException e) {
            return "{}";
        }
    }

    public static String firstLetterCapital(String myString) {
        try {
            // return  myString.substring(0, 1).toUpperCase() + myString.substring(1);

            String firstLetter = myString.substring(0, 1);

            if (firstLetter.equals("µ")) {
                return firstLetter + myString.substring(1);
            } else {
                return myString.substring(0, 1).toUpperCase() + myString.substring(1);
            }

            //return  myString;

        } catch (Exception e) {
            return "";
        }
    }

    public static String formatDate(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "ddMMyyyyHHmmss");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
            SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            return timeFormat.format(myDate);

        } catch (Exception e) {
            //  e.printStackTrace();
        }

        return "--";


    }


    public static String formatDateMM_DD_YYYY(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
            SimpleDateFormat timeFormat = new SimpleDateFormat("MM-dd-yyyy");
            return timeFormat.format(myDate);

        } catch (Exception e) {
            //  e.printStackTrace();
        }

        return "--";


    }

    public static String formatDateForServer_YYYY_MM_DD(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy HH:mm:ss");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
            return timeFormat.format(myDate);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";


    }


    public static String dateToString(Date _date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("MM-dd-yyyy");
        return timeFormat.format(_date);
    }

    public static String availableDateTime(String EffectiveDateTime, String Issued) {
        try {
            if (!EffectiveDateTime.isEmpty())
                return EffectiveDateTime;
            else if (!Issued.isEmpty())
                return Issued;
        } catch (Exception e) {
            return null;
        }
        return null;

    }

    public static Date StringToDate(String _date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(_date);
            return myDate;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();

    }

    public static Date StringToDate_dd_M_yyyy(String _date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/M/yyyy");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(_date);
            return myDate;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();

    }

    public static Date StringToDate_MM_dd_yy(String _date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(_date);
            return myDate;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();

    }


    public static Date StringToDate_MM_dd_yyyy(String _date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(_date);
            return myDate;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();

    }


    public static Date parseStringToDate(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy HH:mm:ss");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
            return myDate;

        } catch (Exception e) {

            //  e.printStackTrace();
        }
        try {
            return dateFormat.parse("01-01-1800 00:00:00");
        } catch (Exception e) {

        }
        return new Date();
    }

    public static Date parseStringToDateMMDDYY(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
            return myDate;

        } catch (Exception e) {

            //  e.printStackTrace();
        }
        try {
            return dateFormat.parse("01-01-1800 00:00:00");
        } catch (Exception e) {

        }
        return new Date();
    }


    public static int compareDate(String strDate1, String strDate2) {
        try {
            String[] splited = strDate1.split("\\s+");
            strDate1 = splited[0];
            splited = strDate2.split("\\s+");
            strDate2 = splited[0];
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            Date date1 = sdf.parse(strDate1);
            Date date2 = sdf.parse(strDate2);

            return date1.compareTo(date2);


        } catch (Exception e) {

        }
        return -2;
    }


    public static String getDateFromDateTimeString(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy HH:mm:ss");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
            SimpleDateFormat timeFormat = new SimpleDateFormat("MM-dd-yyyy");
            return timeFormat.format(myDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "--";
    }


    public static String getDateFromDateTimeStringForDoctorInteraction(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy HH:mm:ss");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
            return timeFormat.format(myDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "--";
    }

    public static String getTimeFromDateTimeString(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM-dd-yyyy HH:mm:ss");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(dateTime);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            return timeFormat.format(myDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "--";
    }


    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    public static String getCurrentDateTime(int type) {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = null;
        if (type == 1) {
            df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        } else if (type == 2) {
            df = new SimpleDateFormat("ddMMyyyyHHmmss");
        } else if (type == 3) {
            df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        } else if (type == 4) {
            df = new SimpleDateFormat("yyyy-MM-dd");
        }
        String formattedDate = df.format(c);
        return formattedDate;
    }

    public static String currencyFormat(String amount) {
        if (amount.length() > 1) {
            String tmp = amount.substring(0, (amount.length() - 2)) + "." + amount.substring((amount.length() - 2), amount.length());
            DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
            return formatter.format(Double.parseDouble(tmp));
        } else {
            String tmp = amount.substring(0, (amount.length() - 1)) + ".0" + amount.substring((amount.length() - 1), amount.length());
            DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
            return formatter.format(Double.parseDouble(tmp));
        }
    }


    public static String currencyFormatBackward(String amount) {

        Double tmpNum = Double.parseDouble(amount);
        tmpNum = tmpNum / 100;

        String tmpStr = tmpNum + "";

        String beforeDecimal = tmpStr.substring(0, tmpStr.indexOf("."));
        String afterDecimal = null;
        try {
            afterDecimal = tmpStr.substring(tmpStr.indexOf(".") + 1);
            if (afterDecimal == null) {
                afterDecimal = tmpStr.substring(tmpStr.indexOf(".") + 1, tmpStr.indexOf(".") + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (Integer.parseInt(afterDecimal) > 0) {
            return beforeDecimal + "." + afterDecimal;
        } else {
            return beforeDecimal;
        }


    }


    public static String cnicFormat(String cnic) {
        String formattedCnic = null;
        try {
            formattedCnic = cnic.substring(0, 5) + "-" +
                    cnic.substring(5, 12) + "-" +
                    cnic.substring(12);
            return formattedCnic;

        } catch (Exception e) {
            return cnic;

        }


    }


    public static String getMobileNetworkCode(String name) {
        String[] networkArr = "MOBILINK PREPAID|MOBILINK POSTPAID|TELENOR PREPAID|TELENOR POSTPAID|UFONE PREPAID|UFONE POSTPAID|WARID PREPAID|WARID POSTPAID|ZONG PREPAID|ZONG POSTPAID".split("\\|");
        String[] networkCodeArr = "MBLINK01|MBLINK02|TELNOR01|TELNOR02|UFONE001|UFONE002|WARID001|WARID002|ZONG0001|ZONG0002".split("\\|");

        for (int i = 0; i < networkArr.length; i++) {

            if (networkArr[i].equals(name)) {
                return networkCodeArr[i];
            }
        }

        return "";
    }

    public static String getMobileNetworkFromCode(String name) {
        String[] networkArr = "MOBILINK PREPAID|MOBILINK POSTPAID|TELENOR PREPAID|TELENOR POSTPAID|UFONE PREPAID|UFONE POSTPAID|WARID PREPAID|WARID POSTPAID|ZONG PREPAID|ZONG POSTPAID".split("\\|");
        String[] networkCodeArr = "MBLINK01|MBLINK02|TELNOR01|TELNOR02|UFONE001|UFONE002|WARID001|WARID002|ZONG0001|ZONG0002".split("\\|");

        for (int i = 0; i < networkArr.length; i++) {

            if (networkCodeArr[i].equals(name)) {
                return networkArr[i];
            }
        }

        return "";
    }


    public static boolean isEmpty(String text) {
        return text.equals("");
    }

    public static boolean isFullname(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }

}
