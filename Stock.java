import java.util.LinkedList;
import java.util.List;
import java.util.Random;
//주식들의 정보를 저장할 class
public class Stock implements Getter{
	private String name;
	private double companyValue;
	private double updownRate;
	List<Integer> chart = new LinkedList<>();//등락률을 저장한 collection framework인 Linked list
	public Stock() {//default 생성자
		this.name=null;
		this.companyValue=0;
		this.updownRate=0;
		this.chart.add(0);
	}
	public Stock(String n,double v) {//생성자
		this.name=n;
		this.companyValue=v;
		this.updownRate=0;
		this.chart.add(0);
	}
	@Override
	public double getValue() {//현재 주식의 가격 반환,Getter interface의 method override
		return companyValue;
	}
	@Override
	public String getName() {//Getter interface의 method override
		return name;
	}
	public void updateStock() {//주식의 가격을 갱신하는 method
		double v= this.companyValue;//현재 가격 저장
		Random rand=new Random();//random 객체 사용
		double updateV = ((rand.nextDouble()%0.55)-0.25);//나머지 연산으로 범위를 0.30~-0.25로 제한
		updateV=Math.round(updateV*100)/100.0;//double 값이라 Math template를 활용해 소수점을 2째자리 까지 생략
		this.updownRate=updateV*100;//100분위로 표기
		this.companyValue=v+(v*updateV);//value를 현재 value 값을 이용해 update
		this.chart.add((int)(updateV*100));//등락률을 저장하는 list에 등락률 저장
	}
	
	public void showTodayValue() {//주식의 정보 출력하는 method
		System.out.println("기업 명 : "+name);
		System.out.println("오늘 주가(1주의 가격) : "+(int)companyValue+"원");
		System.out.println("전일대비 등락률 : "+getUpdownRate()+"%\n");
	}
	
	public double getUpdownRate() {//주식의 등락률을 반환하는 method
		updownRate=Math.round(updownRate*100)/100.0;//소수점 둘째 자리까지 자르고 반환
		return updownRate;
	}
}

	@SuppressWarnings("serial") class ReadPropertyException extends Exception{//입력 값이 유효하지 않을때를 catch하기 위한 Exception을 확장한 class
		public ReadPropertyException() {//예외처리 class
			System.out.println("유효하지 않은 수가 입력되었습니다.");
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}
}
