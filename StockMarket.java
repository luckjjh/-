import java.util.Iterator;
import java.util.List;
//������� �Է¿� ���� ���� ��� ���� class
class StockMarket {
		private static int date;//date�� ��� ���ÿ� �����ؾ� �ϹǷ� static int type ������ ����
		private List<Stock> nowStock;
		@SuppressWarnings("static-access")
		public StockMarket(List<Stock> inputStock) {//������
			this.date=1;
			this.nowStock=inputStock;
		}
		public void nextDate() {//�������� ���� method
			date++;
		}
		public int getDate() {//���� ��¥�� ��ȯ�ϴ� method
			return date;
		}
		
		public void buyStock(MyAccount m,Stock s,int stockNum) throws ReadPropertyException {//�ֽ� ���� method
			if(m.myProperty<(s.getValue()*stockNum)) {//precondition���� ���� �����ݺ��� ������ �ֽ��� ������ ū ��� method ���� x
				System.out.println("Error : ���� �ڻ�(������)�� �����մϴ�.");
				return;
			}
			else {//�ռ� precondition �����Ѵٸ� method ���� ����
				if(stockNum<0)
					throw new ReadPropertyException();
				m.myProperty-=(s.getValue()*stockNum);//�����ݿ��� ������ �ֽ��� ����*������ ��
				int count=m.myAccountMap.get(s.getName());//count �ν��Ͻ��� ���� myAccountMap�� �����Ϸ��� �ֽ��� ���� ������ ����
				count+=stockNum;//���� �ֽ� ���� + ���� �ֽ� ����
				m.myAccountMap.put(s.getName(), count);//count������ �ֽ� ���� ���� �缳��
				System.out.println(s.getName()+"�ֽ� "+stockNum+"�� �� ���ŵǾ����ϴ�.");
			}
		}
		
		public void sellStock(MyAccount m, Stock s, int stockNum) throws ReadPropertyException {//�ֽ� �Ǹ� method
			if(m.myAccountMap.get(s.getName())<stockNum) {//precondition���� ���� ���� �ֽĺ��� �Ǹ��Ϸ��� �ֽ��� ū ��� ���� x
				System.out.println("Error : �ŵ��� �ֽ��� ������ ��ȿ���� �ʽ��ϴ�.");
				return;
			}
			else {//�ռ� precondition �����Ѵٸ� method ���� ����
				if(stockNum<0)
					throw new ReadPropertyException();
				m.myProperty+=(s.getValue()*stockNum);//�����ݿ� �Ǹ��� �ֽ��� ����*���� ����
				int count = m.myAccountMap.get(s.getName());//count �ν��Ͻ��� ���� myAccountMap�� �Ǹ��Ϸ��� �ֽ� ���� ���� ����
				count-=stockNum;//�Ǹ��ϴ� ������ŭ ����
				m.myAccountMap.put(s.getName(), count);//count������ �ֽ� ���� ���� �缳��
				System.out.println(s.getName()+"�ֽ� "+stockNum+"�� �� �ǸŵǾ����ϴ�.");
			}
		}
		
		public void showTodayStock() {//���� stockMarket�� ����� �ֽĵ��� �̸��� ������ ����ϴ� method
			for(Stock s:nowStock) {
				s.showTodayValue();//stock�� �̸��� ���� ���
			}
		}
		public void showChart(String companyName) {//�Է��� ����� �ְ� ������ ��Ʈ�� ���
			int date = 1;
			int yesterday=25;
			int error=0;
			for(Stock a : nowStock) {//for if ������ �Է¹��� �ֽİ� ���� �̸��� ���� �ֽ��� nowStock���� ã��
				if(a.getName().compareToIgnoreCase(companyName)==0) {
					for(Iterator<Integer> it=a.chart.iterator();it.hasNext();) {//iterator ����� �ش� �ֽ��� ������� ����� array�� Ž��
						System.out.print((date++) + "����");
						int n = it.next();
						for(int i =0;i<((yesterday)+n);i++)//���� ��� ������ ��ŭ '*' �� �̿��� ��Ʈ ���·� ǥ��
							System.out.print("*");
						System.out.print("("+n + "%)" );
						System.out.println("");
						yesterday=(yesterday)+n;//���� �ݺ��� ����� ���� ����
					}
					error++;
				}
			}
			if(error==0) {
				System.out.println("Error : ��ȿ���� ���� ������� �Է��Ͽ����ϴ�.");
				System.out.println("�ʱ� �Է� â���� ���ư��ϴ�.");
			}
		}
		enum Company {//ȸ���� ������ ������ ������ �� enum
			Samsung("����/����",124),Microsoft("��ǻ��/SW",1756),Apple("����/����",2350),TESLA("����/�ڵ���",650),
			LG("����/����",100),Kakao("SW/����",18),NVIDIA("����/����",200),Naver("SW/����",14),Huawei("����/����",2),AMD("����/����",180);
			int companyValue;
			String whatCompay;
			private Company(String wC, int cV) {
				this.companyValue=cV;
				this.whatCompay=wC;
			}
			@Override
			public String toString() {
				return this.name()+" : "+this.whatCompay+" / �ð��Ѿ�(�� ����) : "+this.companyValue;
			}
		}
		public void showCompanyInform(String inputCompany) {//ȸ�� ���� ����ϴ� method ������ �ڷᰪ�� �̿�
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
					System.out.println("��ȿ���� ���� ������� �Է��߽��ϴ�."); break;		
				}
			}
		}