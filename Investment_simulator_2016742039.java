import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//\\\\\\\\\\\\\\이 파일이 main 문이 있는 파일입니다.\\\\\\\\\\\\\\
public class Investment_simulator_2016742039 {
//2016742039 정정현
	public static void main(String[] args) throws ReadPropertyException {
		System.out.println("2016742039 정정현 기말고사 대체과제 \n"+"==========모의 주식 프로그램==========\n");
		System.out.println("다음날로 넘길 때 마다 각 기업의 주가가 갱신 됩니다. 주가 등락률은 30~-25%이고 30일차가 되면 프로그램이 종료됩니다.\n");
		System.out.println("이름(문자열)과 초기 자본(int type)을 입력해주시길 바랍니다. 초기 자본은 100,000원 이상을 추천드립니다.");
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);//이름, 초기자본 입력 scanner 객체
		String mName = null;//ID 입력 인스턴스
		double initProperty = 0;//초기 자본 입력 인스턴스
		try {//정수형이 아닌 다른 입력 일때 예외 처리
			System.out.print("이름(ID) : ");
			mName=sc.next();//이름 저장
			System.out.print("초기자본 : ");
			initProperty=sc.nextDouble();//초기 자본 입력
		}
		catch(InputMismatchException e) {
			System.out.println("유효하지 않은 초기 자본을 입력하셨습니다.");
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);//입력 조건을 만족하지 않으면 프로그램 실행 x
		}
		
		List<Stock>eachCompanyStock=new ArrayList<>();
		eachCompanyStock.add(new Stock("Samsung",10000));eachCompanyStock.add(new Stock("MicroSoft",15000));eachCompanyStock.add(new Stock("TESLA",10000));
		eachCompanyStock.add(new Stock("Apple",20000));eachCompanyStock.add(new Stock("LG",10000));eachCompanyStock.add(new Stock("Kakao",10000));
		eachCompanyStock.add(new Stock("Naver",10000));eachCompanyStock.add(new Stock("NVIDIA",5000));eachCompanyStock.add(new Stock("Huawei",5000));
		eachCompanyStock.add(new Stock("AMD",5000));//주식들을 미리 설정 총 10개
		MyAccount mA=new MyAccount(mName,initProperty, eachCompanyStock);//myAccount class 에 앞서 입력한 값 저장
		System.out.println("====================");
		mA.showMyInform();// 초기 정보 확인
		StockMarket stkMrk=new StockMarket(eachCompanyStock);//주식 시장 동작 제어할 객체 생성
		System.out.println("모의 주식 프로그램을 실행합니다.\n");
		
		while(true) {//사용자 입력 반복
			System.out.println(stkMrk.getDate()+"일차 주식시장 입니다.");
			System.out.println("동작을 제어할 숫자(int type)를 입력해주세요");
			System.out.println("0 프로그램 종료 || 1 현재 보유 주식 현황(총 자산 현황) ||"
					+ "2 금일 주가 확인 || 3 금일 주가 오름차순 확인 || 4 금일 주가 내림차순 확인 || 5 매수(주식 구매) || 6 매도(주식 판매) ||"
					+ "7 주식 차트로 확인 || 8 기업 정보 확인 || 9 다음날로 넘기기");
			int inputNum = 0;
			try {
				inputNum = sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("잘못 된 입력입니다.");//정수 형이 아닌 입력 들어올 때 예외처리
			}
			if(inputNum==0||stkMrk.getDate()==30) 
				break;//0입력 되거나 30일차가 넘어 가면 프로그램 종료
			switch(inputNum){//switch case 문 활용해 각 입력에 따른 동작 제어
			case 1:{
				mA.showMyInform();
				break;//현재 자신의 자산 정보 출력
			}
			case 2:{
				stkMrk.showTodayStock();
				break;//금일 주식 정보 출력
			}
			case 3:{//기존에 입력된 주식들을 가격기준 오름차순으로 정렬해 출력
				Collections.sort(eachCompanyStock,(company1,company2)->(int)company1.getValue()-(int)company2.getValue());//람다 표현식 활용해 오름차순 출력
				for(Stock s : eachCompanyStock)
					s.showTodayValue();
				break;
			}
			case 4:{//기존 입력된 주식들 가격기준 내림차순으로 정렬해 출력
				Collections.sort(eachCompanyStock,(company1,company2)->(int)company2.getValue()-(int)company1.getValue());
				//람다 표현식 활용
				for(Stock s : eachCompanyStock)
					s.showTodayValue();
				break;
			}
			case 5:{//주식 구매 (매수)
				System.out.println("구매할 주식의 기업 이름과 개수를 입력하세요");
				System.out.print("기업의 이름 : ");
				String company = sc.next();
				System.out.print("개수 : ");
				int num = sc.nextInt();
				int error=0;
				for(Stock a : eachCompanyStock) {//입력받은 stock의 이름과 일치하는 stock를 array에서 찾음
					if(company.compareToIgnoreCase(a.getName())==0) {
						stkMrk.buyStock(mA, a, num);//주식 구매(매수) method
						error++;//일치하는 stock이 있는 경우 error를 1 증가시킴
						break;
					}
				}
				if(error==0) {//error가 1 증가 되지 않는 다면 유효하지 않은 기업명을 입력 한 것이라 에러 출력
					System.out.println("Error : 유효하지 않은 기업명을 입력하였습니다.");
					System.out.println("초기 입력 창으로 돌아갑니다.");
				}
				break;
			}
			case 6:{
				System.out.println("판매할 주식의 기업 이름과 개수를 입력하세요");
				System.out.print("기업의 이름 : ");
				String company = sc.next();
				System.out.print("개수 : ");
				int num = sc.nextInt();
				int error=0;
				for(Stock a : eachCompanyStock) {//입력받은 stock의 이름과 일치하는 stock를 array에서 찾음
					if(company.compareToIgnoreCase(a.getName())==0) {
						stkMrk.sellStock(mA, a, num);//주식 판매(매도) method
						error++;
						break;
					}
				}
				if(error==0) {//앞선 매수 method와 동일한 원리로 error 검출
					System.out.println("Error : 유효하지 않은 기업명을 입력하였습니다.");
					System.out.println("초기 입력 창으로 돌아갑니다.");
				}
				break;
			}
			case 7:{//입력받은 주식의 차트를 출력하는 method 실행
				System.out.println("차트를 확인할 기업의 이름을 입력하세요");
				System.out.print("기업의 이름 : ");
				String company = sc.next();
				stkMrk.showChart(company);
				break;
			}
			case 8:{
				System.out.println("정보를 확인할 기업명을 입력하세요");
				System.out.println("정보 확인 가능한 기업 명 : Samsung, Microsoft, TESLA, Apple, LG, Kakao, NVIDIA, Naver, Huawei, AMD");
				System.out.print("기업의 이름 : ");
				String input = sc.next();
				stkMrk.showCompanyInform(input);//Enum 열거 값을 활용해 회사의 정보 출력하는 method
				break;
			}
			case 9:{//다음 날로 넘기면서 eachCompanyStcok에 있는 정보를 updateStock method를 통해 갱신
				stkMrk.nextDate();
				for(Stock s: eachCompanyStock)
					s.updateStock();
				break;
			}
			default : {//유효하지 않은 입력 값
				System.out.println("Error : 유효하지 않은 입력입니다. 다시 입력해 주십시오.");
			}
			}
		}
		System.out.println("프로그램을 종료합니다.");
		System.out.println("==========최종 수익==========");
		mA.showMyGain();//최종 수익 출력 method
	}	
}

