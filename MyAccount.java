import java.util.HashMap;
import java.util.List;
import java.util.Set;
//사용자의 정보와 자산을 저장할 class
class MyAccount implements Getter{
		private String name;
		private double initProperty;
		double myProperty;
		HashMap<String,Integer> myAccountMap;//주식의 이름과 현재 보유한 개수를 저장할 collection framework인 HashMap
		private List<Stock> nowStock;
		public MyAccount(String n,double p,List<Stock> Stock) throws ReadPropertyException {//초기 값을 저장할 생성자
			this.name=n;
			if(p <0) {//초기 자산으로 음수 값이 입력되면 안되므로 ReadPropertyException으로 error 제어
				throw new ReadPropertyException();
			}
			this.myProperty=p;
			this.initProperty=p;//프로그램 종료 후 수익을 계산하기 위해 입력 초기값 저장
			this.nowStock=Stock;
			this.myAccountMap = new HashMap<>();
			for(Stock a :Stock) {//초기에는 아무런 주식도 구매하지 않은 상태이기 때문에 주식 이름과 그 주식의 개수를 보관하는 Hashmap의 value를 0으로 설정
				myAccountMap.put(a.getName(), 0);
			}
		}
		
		public void showMyInform() {//현재 자신의 정보 확인
			System.out.println("이름 : "+name);
			System.out.println("예수금 : "+(int) myProperty+"원");//주식을 구매하고 남은 금액
			System.out.println("현재 총 보유 자산 : "+(int)getAllProperty()+"원");//예수금과 보유한 주식의 가격을 합친 금액
			System.out.println("현재 보유 주식 현황\n====================");
			Set<String> stockNameSet = myAccountMap.keySet();//HashMap의 key를 탐색해 해당 key의 value와 key를 출력
			for(String s :stockNameSet) {
				if(myAccountMap.get(s)!=0) {//value 값이 0이 아닌 key만 출력
					System.out.println("기업 이름 : "+s.toString()+"\n보유 주식 개수 / 가격 : "+myAccountMap.get(s)+"개");
					for(Stock a : nowStock) {//key의 value와 key의 이름을 갖는 주식의 가격을 곱해 현재 가격 출력
						if(a.getName()==s)
							System.out.println((int)a.getValue()*myAccountMap.get(s)+"원");
					}
				}
			}
			
		}
		@Override
		public String getName() {//Getter interface의 method override
			return name;
		}
		@Override
		public double getValue() {//Getter interface의 method override
			return myProperty;
		}
		
		public double getAllProperty() {//현재 총 자산 출력
			double stockProperty=0;
			Set<String> stockNameSet = myAccountMap.keySet();//myAccountMap에서 주식의 이름인 key만 set으로 선언
			for(String s :stockNameSet)//set을 훑어 보며 이름이 stock와 같다면 현재 stock의 가격과 set의 개수를 곱해 stockProperty에 저장
				for(Stock a: nowStock) {
					if(a.getName()==s)
						stockProperty+=(myAccountMap.get(s)*a.getValue());
				}
			return myProperty+stockProperty;//예수금과 계산한 값 합산
		}
		
		public void showMyGain() {//최종 수익률을 계산
			System.out.println(name+"님의 모의주식 최종결과" );
			System.out.println("초기 자본금 : "+initProperty);
			System.out.println("최종 자본금 : "+(int)getAllProperty());
			System.out.println("최종 수익률 : "+(int)((getAllProperty()/initProperty*100)-100)+"%");
		}
	}