package com.my.exe;


	import org.apache.http.HttpResponse;
	import org.apache.http.HttpStatus;
	import org.apache.http.client.HttpClient;
	import org.apache.http.client.methods.HttpPost;
	import org.apache.http.entity.StringEntity;
	import org.apache.http.impl.client.HttpClients;
	import org.apache.http.util.EntityUtils;
	 
	 
	public class ChatbotSend {
	 
	    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=a5542d357fd999d1d6764d46ee9a49b505e536f0e3b1d45bb2d4685199187378";
	 
	    public static void main(String args[]) throws Exception{
	    	/*Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.HOUR_OF_DAY, 13);
	        calendar.set(Calendar.MINUTE, 46);
	        calendar.set(Calendar.SECOND, 0);

	        Date time = calendar.getTime();

	        Timer timer = new Timer();
	        timer.scheduleAtFixedRate(new TimerTask() {
	            public void run() {
	            	try {
						botStart("15732151920",WEBHOOK_TOKEN,"我是报饭的，怕你饿到");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	        
*/	   
	    //	botStart("15732151953",WEBHOOK_TOKEN,"没什么好聊的"); //乔俊雪
	    //	botStart("15732151915",WEBHOOK_TOKEN,"聪明伶俐的我什么都干(*^▽^*)"); //郝志文
	    //	botStart("15732151920",WEBHOOK_TOKEN,"聪明伶俐的我什么都干(*^▽^*)"); //王丽
	    //	botStart("15731176811",WEBHOOK_TOKEN,"聪明伶俐的我什么都干(*^▽^*)"); //培玉
	    //	botStart("15732151922",WEBHOOK_TOKEN,"哈哈哈哈"); //欢欢
	    }
	  public static  void botStart(String httpmsg,String msg) throws Exception{
		  HttpClient httpclient = HttpClients.createDefault();
			 
	        HttpPost httppost = new HttpPost(httpmsg);
	        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
	 
	       String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\":\""+msg+"\"},\"at\": {\"isAtAll\":true}}";
	        //String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"提醒大家吃饭的@15732151920\"},\"at\": {\"isAtAll\":false}}";
	        System.out.println(textMsg);
	        StringEntity se = new StringEntity(textMsg, "utf-8");
	        httppost.setEntity(se);
	 
	        HttpResponse response = httpclient.execute(httppost);
	        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
	            String result= EntityUtils.toString(response.getEntity(), "utf-8");
	            System.out.println(result);
	        }
	    }
	  public static  void botStart(String mobile,String httpmsg,String msg) throws Exception{
		  HttpClient httpclient = HttpClients.createDefault();
			 
	        HttpPost httppost = new HttpPost(httpmsg);
	        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
	 
	       //String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\":\""+msg+"\"},\"at\": {\"isAtAll\":true}}";
	        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \""+msg+"@"+mobile+"\"},\"at\": {\"atMobiles\":[\""+mobile+"\"],\"isAtAll\":false}}";
	        System.out.println(textMsg);
	        StringEntity se = new StringEntity(textMsg, "utf-8");
	        httppost.setEntity(se);
	 
	        HttpResponse response = httpclient.execute(httppost);
	        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
	            String result= EntityUtils.toString(response.getEntity(), "utf-8");
	            System.out.println(result);
	        }
	    }
	}

