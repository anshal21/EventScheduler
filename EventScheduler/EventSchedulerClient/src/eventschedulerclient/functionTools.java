    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventschedulerclient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import jdk.nashorn.internal.runtime.JSType;

/**
 *
 * @author anshal
 */
public class functionTools {
       public int getMinute(String Time){
        String ans="";
        if(Time.charAt(2)!='0'){
            ans=ans+Time.charAt(2);
        }
        ans=ans+Time.charAt(3);
        int an=Integer.parseInt(ans);
        return an;
    }
    public int getHour(String Time){
        String ans="";
        if(Time.charAt(0)!='0'){
            ans=ans+Time.charAt(0);
        }
        ans=ans+Time.charAt(1);
        int an=Integer.parseInt(ans);
        return an;
    }
    public int compareTime(String time1,String time2){
       
        int hr1=getHour(time1);
        int min1=getMinute(time1);
        int hr2=getHour(time2);
        int min2=getMinute(time2);
        if(hr1<hr2)
            return -1;
        else if(hr1>hr2)
            return 1;
        else{
            if(min1<min2)
                return -1;
            else if(min2>min1)
                return 1;
            else
                return 0;
        }
    }
    public int getYear(String date){
        String y="";
        y=date.substring(0,4);
        return Integer.parseInt(y);
    }
    public int getMonth(String date){
        
      //  System.out.println("here in getMonth "+date);
        String m="";
         if(date.charAt(5)!='0'){
            m=m+date.charAt(5);
        }
        m=m+date.charAt(6);
        return Integer.parseInt(m);
    }
    public int getDay(String date){
        String d="";
        if(date.charAt(8)!='0')
            d=d+date.charAt(8);
        d=d+date.charAt(9);
        return Integer.parseInt(d);
    }
    public int compareDates(String date1,String date2){
       // System.out.println("compareDates :"+date1+" "+date2);
       int year1=getYear(date1);
       int month1=getMonth(date1);
       int day1=getDay(date1);
       int year2=getYear(date2);
       int month2=getMonth(date2);
       int day2=getDay(date2);
       if(year1<year2){
           return -1;
       }
       else if(year1>year2){
           return 1;
       }
       else{
           if(month1<month2)
               return -1;
           else if(month1>month2)
               return 1;
           else{
               if(day1<day2)
                   return -1;
               else if(day1>day2)
                   return 1;
               else
                   return 0;
           }
       }
    }
    public String makeDate(int year,int month,int day){
        String yr=""+year;
        String mon="";
        String d="";
        if(month<10)
            mon=mon+0;
        mon=mon+month;
        if(day<10)
            d=d+0;
        d=d+day;
       
        String date=yr+"-"+mon+"-"+d;
        return date;
    }
    public String incrementDate(String Date){
        int year=getYear(Date);
        int month=getMonth(Date);
        int day=getDay(Date);
        int lim[]={0,31,28,31,30,31,30,31,31,30,31,30,31};
        if((year%4==0&&year%100!=0)||(year%400==0)){
            lim[2]+=1;
        }
       // System.out.println("and the month is"+month);
        int nDay=(day%lim[month])+1;
        int nMonth = 0;
        if(nDay<day){
            nMonth=(month%12)+1;
        }
        else
            nMonth=month;
        if(nMonth<month){
            year=year+1;
        }
        return makeDate(year,nMonth,nDay);
        
        
    }
    public boolean validateDate(String date){
        if(!JSType.isNumber(date.substring(0,4)))
               return false;
        if(!JSType.isNumber(date.substring(5,7)))
            return false;
        if(!JSType.isNumber(date.substring(8))){
            return false;
        }
        Calendar cal=Calendar.getInstance();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String d=sdf.format(cal.getTime());
        System.out.println("current date : "+d);
        if(compareDates(d, date)==1){
            return false;
        }
        return true;
        
    }
    public boolean isValidWindow(String date1,String date2){
        for(int i=0;i<=10;i++){
            String date=date1;
            for(int j=0;j<i;j++){
                date=incrementDate(date);
            }
            if(date.equals(date2))
                return true;
        }
        
        return false;
    }
    public String paddString(String s,int len){
        if(s==null){
            System.out.println("got a null String :)");
            return s;
        }
        for(int i=s.length();i<len;i++){
            s=s+" ";
        }
        return s;
    }
}
