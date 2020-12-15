import java.util.LinkedList;
import java.util.List;
import java.util.Random;
//�ֽĵ��� ������ ������ class
public class Stock implements Getter{
	private String name;
	private double companyValue;
	private double updownRate;
	List<Integer> chart = new LinkedList<>();//������� ������ collection framework�� Linked list
	public Stock() {//default ������
		this.name=null;
		this.companyValue=0;
		this.updownRate=0;
		this.chart.add(0);
	}
	public Stock(String n,double v) {//������
		this.name=n;
		this.companyValue=v;
		this.updownRate=0;
		this.chart.add(0);
	}
	@Override
	public double getValue() {//���� �ֽ��� ���� ��ȯ,Getter interface�� method override
		return companyValue;
	}
	@Override
	public String getName() {//Getter interface�� method override
		return name;
	}
	public void updateStock() {//�ֽ��� ������ �����ϴ� method
		double v= this.companyValue;//���� ���� ����
		Random rand=new Random();//random ��ü ���
		double updateV = ((rand.nextDouble()%0.55)-0.25);//������ �������� ������ 0.30~-0.25�� ����
		updateV=Math.round(updateV*100)/100.0;//double ���̶� Math template�� Ȱ���� �Ҽ����� 2°�ڸ� ���� ����
		this.updownRate=updateV*100;//100������ ǥ��
		this.companyValue=v+(v*updateV);//value�� ���� value ���� �̿��� update
		this.chart.add((int)(updateV*100));//������� �����ϴ� list�� ����� ����
	}
	
	public void showTodayValue() {//�ֽ��� ���� ����ϴ� method
		System.out.println("��� �� : "+name);
		System.out.println("���� �ְ�(1���� ����) : "+(int)companyValue+"��");
		System.out.println("���ϴ�� ����� : "+getUpdownRate()+"%\n");
	}
	
	public double getUpdownRate() {//�ֽ��� ������� ��ȯ�ϴ� method
		updownRate=Math.round(updownRate*100)/100.0;//�Ҽ��� ��° �ڸ����� �ڸ��� ��ȯ
		return updownRate;
	}
}

	@SuppressWarnings("serial") class ReadPropertyException extends Exception{//�Է� ���� ��ȿ���� �������� catch�ϱ� ���� Exception�� Ȯ���� class
		public ReadPropertyException() {//����ó�� class
			System.out.println("��ȿ���� ���� ���� �ԷµǾ����ϴ�.");
			System.out.println("���α׷��� �����մϴ�.");
			System.exit(0);
		}
}
