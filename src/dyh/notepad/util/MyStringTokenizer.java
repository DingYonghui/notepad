package dyh.notepad.util;


import java.util.StringTokenizer;

public class MyStringTokenizer extends StringTokenizer {

	String sval = "";
	String oldStr,str;
	int m_currPosition = 0;
	int m_beginPosition = 0 ;
	public MyStringTokenizer(String str) {
		super(str,"");
		this.oldStr = str ;
		this.str = str ;
	}
	
	public String nextToken(){
		try{
			String s = super.nextToken();
			int pos = -1 ;
			if(oldStr.equals(s)){
				return s ;
			}
			
			pos = str.indexOf(s+sval);
			
			if( pos== -1){
				pos = str.indexOf(sval+s);
				if(pos== -1)
					return null;
				else
					pos += 1 ;
				
			}
			
			int xBegin = pos + s.length();
			str = str.substring(xBegin);
			
			m_currPosition = m_beginPosition + pos;
			m_beginPosition = m_beginPosition + xBegin ;
			
			return s ;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//返回token在字符串中的位置
	public int getCurrPosition(){
		return m_currPosition ;
	}

}
