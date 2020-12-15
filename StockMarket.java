import java.util.Iterator;
import java.util.List;
//사용자의 입력에 따른 동작 제어를 위한 class
class StockMarket {
		private static int date;//date는 모두 동시에 증가해야 하므로 static int type 변수로 지정
		private List<Stock> nowStock;
		@SuppressWarnings("static-access")
		public StockMarket(List<Stock> inputStock) {//생성자
			this.date=1;
			this.nowStock=inputStock;
		}
		public void nextDate() {//다음날로 가는 method
			date++;
		}
		public int getDate() {//현재 날짜를 반환하는 method
			return date;
		}
		
		public void buyStock(MyAccount m,Stock s,int stockNum) throws ReadPropertyException {//주식 구매 method
			if(m.myProperty<(s.getValue()*stockNum)) {//precondition으로 현재 예수금보다 구매할 주식의 가격이 큰 경우 method 동작 x
				System.out.println("Error : 현재 자산(예수금)이 부족합니다.");
				return;
			}
			else {//앞선 precondition 만족한다면 method 구문 진행
				if(stockNum<0)
					throw new ReadPropertyException();
				m.myProperty-=(s.getValue()*stockNum);//예수금에서 구매할 주식의 가격*개수를 뺌
				int count=m.myAccountMap.get(s.getName());//count 인스턴스에 현재 myAccountMap에 구매하려는 주식의 보유 개수를 저장
				count+=stockNum;//보유 주식 개수 + 구매 주식 개수
				m.myAccountMap.put(s.getName(), count);//count값으로 주식 보유 개수 재설정
				System.out.println(s.getName()+"주식 "+stockNum+"개 가 구매되었습니다.");
			}
		}
		
		public void sellStock(MyAccount m, Stock s, int stockNum) throws ReadPropertyException {//주식 판매 method
			if(m.myAccountMap.get(s.getName())<stockNum) {//precondition으로 현재 보유 주식보다 판매하려는 주식이 큰 경우 동작 x
				System.out.println("Error : 매도할 주식의 개수가 유효하지 않습니다.");
				return;
			}
			else {//앞선 precondition 만족한다면 method 구문 진행
				if(stockNum<0)
					throw new ReadPropertyException();
				m.myProperty+=(s.getValue()*stockNum);//예수금에 판매할 주식의 가격*개수 더함
				int count = m.myAccountMap.get(s.getName());//count 인스턴스에 현재 myAccountMap에 판매하려는 주식 보유 개수 저장
				count-=stockNum;//판매하는 개수만큼 빼줌
				m.myAccountMap.put(s.getName(), count);//count값으로 주식 보유 개수 재설정
				System.out.println(s.getName()+"주식 "+stockNum+"개 가 판매되었습니다.");
			}
		}
		
		public void showTodayStock() {//현재 stockMarket에 저장된 주식들을 이름과 가격을 출력하는 method
			for(Stock s:nowStock) {
				s.showTodayValue();//stock의 이름과 가격 출력
			}
		}
		public void showChart(String companyName) {//입력한 기업의 주가 동향을 차트로 출력
			int date = 1;
			int yesterday=25;
			int error=0;
			for(Stock a : nowStock) {//for if 문으로 입력받은 주식과 같은 이름을 같는 주식을 nowStock에서 찾음
				if(a.getName().compareToIgnoreCase(companyName)==0) {
					for(Iterator<Integer> it=a.chart.iterator();it.hasNext();) {//iterator 사용해 해당 주식의 등락률이 저장된 array를 탐색
						System.out.print((date++) + "일차");
						int n = it.next();
						for(int i =0;i<((yesterday)+n);i++)//전일 대비 증감량 만큼 '*' 를 이용해 차트 형태로 표시
							System.out.print("*");
						System.out.print("("+n + "%)" );
						System.out.println("");
						yesterday=(yesterday)+n;//다음 반복때 사용할 값을 저장
					}
					error++;
				}
			}
			if(error==0) {
				System.out.println("Error : 유효하지 않은 기업명을 입력하였습니다.");
				System.out.println("초기 입력 창으로 돌아갑니다.");
			}
		}
		enum Company {//회사의 정보를 저장할 열거형 값 enum
			Samsung("전기/전자",124),Microsoft("컴퓨터/SW",1756),Apple("전기/전자",2350),TESLA("전기/자동차",650),
			LG("전기/전자",100),Kakao("SW/서비스",18),NVIDIA("전기/전자",200),Naver("SW/서비스",14),Huawei("전기/전자",2),AMD("전기/전자",180);
			int companyValue;
			String whatCompay;
			private Company(String wC, int cV) {
				this.companyValue=cV;
				this.whatCompay=wC;
			}
			@Override
			public String toString() {
				return this.name()+" : "+this.whatCompay+" / 시가총액(조 단위) : "+this.companyValue;
			}
		}
		public void showCompanyInform(String inputCompany) {//회사 정보 출력하는 method 열거형 자료값을 이용
				switch(inputCompany) {
				case "Samsung":
					System.out.println(Company.Samsung.toString()); break;
				case "Microsoft":
					System.out.println(Company.Microsoft.toString()); break;
				case "TESLA":
					System.out.println(Company.TESLA.toString()); break;
				case "Apple":
					System.out.println(Company.Apple.toString()); break;
				case "LG":
					System.out.println(Company.LG.toString()); break;
				case "Kakao":
					System.out.println(Company.Kakao.toString()); break;
				case "NVIDIA":
					System.out.println(Company.NVIDIA.toString()); break;
				case "Naver":
					System.out.println(Company.Naver.toString()); break;
				case "Huawei":
					System.out.println(Company.Huawei.toString()); break;
				case "AMD":
					System.out.println(Company.AMD.toString()); break;
				default:
					System.out.println("유효하지 않은 기업명을 입력했습니다."); break;		
				}
			}
		}