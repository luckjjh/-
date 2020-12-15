import java.util.HashMap;
import java.util.List;
import java.util.Set;
//������� ������ �ڻ��� ������ class
class MyAccount implements Getter{
		private String name;
		private double initProperty;
		double myProperty;
		HashMap<String,Integer> myAccountMap;//�ֽ��� �̸��� ���� ������ ������ ������ collection framework�� HashMap
		private List<Stock> nowStock;
		public MyAccount(String n,double p,List<Stock> Stock) throws ReadPropertyException {//�ʱ� ���� ������ ������
			this.name=n;
			if(p <0) {//�ʱ� �ڻ����� ���� ���� �ԷµǸ� �ȵǹǷ� ReadPropertyException���� error ����
				throw new ReadPropertyException();
			}
			this.myProperty=p;
			this.initProperty=p;//���α׷� ���� �� ������ ����ϱ� ���� �Է� �ʱⰪ ����
			this.nowStock=Stock;
			this.myAccountMap = new HashMap<>();
			for(Stock a :Stock) {//�ʱ⿡�� �ƹ��� �ֽĵ� �������� ���� �����̱� ������ �ֽ� �̸��� �� �ֽ��� ������ �����ϴ� Hashmap�� value�� 0���� ����
				myAccountMap.put(a.getName(), 0);
			}
		}
		
		public void showMyInform() {//���� �ڽ��� ���� Ȯ��
			System.out.println("�̸� : "+name);
			System.out.println("������ : "+(int) myProperty+"��");//�ֽ��� �����ϰ� ���� �ݾ�
			System.out.println("���� �� ���� �ڻ� : "+(int)getAllProperty()+"��");//�����ݰ� ������ �ֽ��� ������ ��ģ �ݾ�
			System.out.println("���� ���� �ֽ� ��Ȳ\n====================");
			Set<String> stockNameSet = myAccountMap.keySet();//HashMap�� key�� Ž���� �ش� key�� value�� key�� ���
			for(String s :stockNameSet) {
				if(myAccountMap.get(s)!=0) {//value ���� 0�� �ƴ� key�� ���
					System.out.println("��� �̸� : "+s.toString()+"\n���� �ֽ� ���� / ���� : "+myAccountMap.get(s)+"��");
					for(Stock a : nowStock) {//key�� value�� key�� �̸��� ���� �ֽ��� ������ ���� ���� ���� ���
						if(a.getName()==s)
							System.out.println((int)a.getValue()*myAccountMap.get(s)+"��");
					}
				}
			}
			
		}
		@Override
		public String getName() {//Getter interface�� method override
			return name;
		}
		@Override
		public double getValue() {//Getter interface�� method override
			return myProperty;
		}
		
		public double getAllProperty() {//���� �� �ڻ� ���
			double stockProperty=0;
			Set<String> stockNameSet = myAccountMap.keySet();//myAccountMap���� �ֽ��� �̸��� key�� set���� ����
			for(String s :stockNameSet)//set�� �Ⱦ� ���� �̸��� stock�� ���ٸ� ���� stock�� ���ݰ� set�� ������ ���� stockProperty�� ����
				for(Stock a: nowStock) {
					if(a.getName()==s)
						stockProperty+=(myAccountMap.get(s)*a.getValue());
				}
			return myProperty+stockProperty;//�����ݰ� ����� �� �ջ�
		}
		
		public void showMyGain() {//���� ���ͷ��� ���
			System.out.println(name+"���� �����ֽ� �������" );
			System.out.println("�ʱ� �ں��� : "+initProperty);
			System.out.println("���� �ں��� : "+(int)getAllProperty());
			System.out.println("���� ���ͷ� : "+(int)((getAllProperty()/initProperty*100)-100)+"%");
		}
	}