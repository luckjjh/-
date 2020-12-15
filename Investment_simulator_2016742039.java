import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//\\\\\\\\\\\\\\�� ������ main ���� �ִ� �����Դϴ�.\\\\\\\\\\\\\\
public class Investment_simulator_2016742039 {
//2016742039 ������
	public static void main(String[] args) throws ReadPropertyException {
		System.out.println("2016742039 ������ �⸻��� ��ü���� \n"+"==========���� �ֽ� ���α׷�==========\n");
		System.out.println("�������� �ѱ� �� ���� �� ����� �ְ��� ���� �˴ϴ�. �ְ� ������� 30~-25%�̰� 30������ �Ǹ� ���α׷��� ����˴ϴ�.\n");
		System.out.println("�̸�(���ڿ�)�� �ʱ� �ں�(int type)�� �Է����ֽñ� �ٶ��ϴ�. �ʱ� �ں��� 100,000�� �̻��� ��õ�帳�ϴ�.");
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);//�̸�, �ʱ��ں� �Է� scanner ��ü
		String mName = null;//ID �Է� �ν��Ͻ�
		double initProperty = 0;//�ʱ� �ں� �Է� �ν��Ͻ�
		try {//�������� �ƴ� �ٸ� �Է� �϶� ���� ó��
			System.out.print("�̸�(ID) : ");
			mName=sc.next();//�̸� ����
			System.out.print("�ʱ��ں� : ");
			initProperty=sc.nextDouble();//�ʱ� �ں� �Է�
		}
		catch(InputMismatchException e) {
			System.out.println("��ȿ���� ���� �ʱ� �ں��� �Է��ϼ̽��ϴ�.");
			System.out.println("���α׷��� �����մϴ�.");
			System.exit(0);//�Է� ������ �������� ������ ���α׷� ���� x
		}
		
		List<Stock>eachCompanyStock=new ArrayList<>();
		eachCompanyStock.add(new Stock("Samsung",10000));eachCompanyStock.add(new Stock("MicroSoft",15000));eachCompanyStock.add(new Stock("TESLA",10000));
		eachCompanyStock.add(new Stock("Apple",20000));eachCompanyStock.add(new Stock("LG",10000));eachCompanyStock.add(new Stock("Kakao",10000));
		eachCompanyStock.add(new Stock("Naver",10000));eachCompanyStock.add(new Stock("NVIDIA",5000));eachCompanyStock.add(new Stock("Huawei",5000));
		eachCompanyStock.add(new Stock("AMD",5000));//�ֽĵ��� �̸� ���� �� 10��
		MyAccount mA=new MyAccount(mName,initProperty, eachCompanyStock);//myAccount class �� �ռ� �Է��� �� ����
		System.out.println("====================");
		mA.showMyInform();// �ʱ� ���� Ȯ��
		StockMarket stkMrk=new StockMarket(eachCompanyStock);//�ֽ� ���� ���� ������ ��ü ����
		System.out.println("���� �ֽ� ���α׷��� �����մϴ�.\n");
		
		while(true) {//����� �Է� �ݺ�
			System.out.println(stkMrk.getDate()+"���� �ֽĽ��� �Դϴ�.");
			System.out.println("������ ������ ����(int type)�� �Է����ּ���");
			System.out.println("0 ���α׷� ���� || 1 ���� ���� �ֽ� ��Ȳ(�� �ڻ� ��Ȳ) ||"
					+ "2 ���� �ְ� Ȯ�� || 3 ���� �ְ� �������� Ȯ�� || 4 ���� �ְ� �������� Ȯ�� || 5 �ż�(�ֽ� ����) || 6 �ŵ�(�ֽ� �Ǹ�) ||"
					+ "7 �ֽ� ��Ʈ�� Ȯ�� || 8 ��� ���� Ȯ�� || 9 �������� �ѱ��");
			int inputNum = 0;
			try {
				inputNum = sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("�߸� �� �Է��Դϴ�.");//���� ���� �ƴ� �Է� ���� �� ����ó��
			}
			if(inputNum==0||stkMrk.getDate()==30) 
				break;//0�Է� �ǰų� 30������ �Ѿ� ���� ���α׷� ����
			switch(inputNum){//switch case �� Ȱ���� �� �Է¿� ���� ���� ����
			case 1:{
				mA.showMyInform();
				break;//���� �ڽ��� �ڻ� ���� ���
			}
			case 2:{
				stkMrk.showTodayStock();
				break;//���� �ֽ� ���� ���
			}
			case 3:{//������ �Էµ� �ֽĵ��� ���ݱ��� ������������ ������ ���
				Collections.sort(eachCompanyStock,(company1,company2)->(int)company1.getValue()-(int)company2.getValue());//���� ǥ���� Ȱ���� �������� ���
				for(Stock s : eachCompanyStock)
					s.showTodayValue();
				break;
			}
			case 4:{//���� �Էµ� �ֽĵ� ���ݱ��� ������������ ������ ���
				Collections.sort(eachCompanyStock,(company1,company2)->(int)company2.getValue()-(int)company1.getValue());
				//���� ǥ���� Ȱ��
				for(Stock s : eachCompanyStock)
					s.showTodayValue();
				break;
			}
			case 5:{//�ֽ� ���� (�ż�)
				System.out.println("������ �ֽ��� ��� �̸��� ������ �Է��ϼ���");
				System.out.print("����� �̸� : ");
				String company = sc.next();
				System.out.print("���� : ");
				int num = sc.nextInt();
				int error=0;
				for(Stock a : eachCompanyStock) {//�Է¹��� stock�� �̸��� ��ġ�ϴ� stock�� array���� ã��
					if(company.compareToIgnoreCase(a.getName())==0) {
						stkMrk.buyStock(mA, a, num);//�ֽ� ����(�ż�) method
						error++;//��ġ�ϴ� stock�� �ִ� ��� error�� 1 ������Ŵ
						break;
					}
				}
				if(error==0) {//error�� 1 ���� ���� �ʴ� �ٸ� ��ȿ���� ���� ������� �Է� �� ���̶� ���� ���
					System.out.println("Error : ��ȿ���� ���� ������� �Է��Ͽ����ϴ�.");
					System.out.println("�ʱ� �Է� â���� ���ư��ϴ�.");
				}
				break;
			}
			case 6:{
				System.out.println("�Ǹ��� �ֽ��� ��� �̸��� ������ �Է��ϼ���");
				System.out.print("����� �̸� : ");
				String company = sc.next();
				System.out.print("���� : ");
				int num = sc.nextInt();
				int error=0;
				for(Stock a : eachCompanyStock) {//�Է¹��� stock�� �̸��� ��ġ�ϴ� stock�� array���� ã��
					if(company.compareToIgnoreCase(a.getName())==0) {
						stkMrk.sellStock(mA, a, num);//�ֽ� �Ǹ�(�ŵ�) method
						error++;
						break;
					}
				}
				if(error==0) {//�ռ� �ż� method�� ������ ������ error ����
					System.out.println("Error : ��ȿ���� ���� ������� �Է��Ͽ����ϴ�.");
					System.out.println("�ʱ� �Է� â���� ���ư��ϴ�.");
				}
				break;
			}
			case 7:{//�Է¹��� �ֽ��� ��Ʈ�� ����ϴ� method ����
				System.out.println("��Ʈ�� Ȯ���� ����� �̸��� �Է��ϼ���");
				System.out.print("����� �̸� : ");
				String company = sc.next();
				stkMrk.showChart(company);
				break;
			}
			case 8:{
				System.out.println("������ Ȯ���� ������� �Է��ϼ���");
				System.out.println("���� Ȯ�� ������ ��� �� : Samsung, Microsoft, TESLA, Apple, LG, Kakao, NVIDIA, Naver, Huawei, AMD");
				System.out.print("����� �̸� : ");
				String input = sc.next();
				stkMrk.showCompanyInform(input);//Enum ���� ���� Ȱ���� ȸ���� ���� ����ϴ� method
				break;
			}
			case 9:{//���� ���� �ѱ�鼭 eachCompanyStcok�� �ִ� ������ updateStock method�� ���� ����
				stkMrk.nextDate();
				for(Stock s: eachCompanyStock)
					s.updateStock();
				break;
			}
			default : {//��ȿ���� ���� �Է� ��
				System.out.println("Error : ��ȿ���� ���� �Է��Դϴ�. �ٽ� �Է��� �ֽʽÿ�.");
			}
			}
		}
		System.out.println("���α׷��� �����մϴ�.");
		System.out.println("==========���� ����==========");
		mA.showMyGain();//���� ���� ��� method
	}	
}

