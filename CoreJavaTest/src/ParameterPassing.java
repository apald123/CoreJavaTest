
public class ParameterPassing {
	
	public class Inner {
		public String name;
		public int age;
		
		@Override
		public String toString() {
			return "Inner [name=" + name + ", age=" + age + "]";
		}		
	}
	
	public void foo(Inner in1) {
		Inner in2 = this.new Inner();
		in2.name = "in2";
		in2.age = 100;
		in1 = in2;
	}
	
	public void bar() {
		Inner in1 = new Inner();
		in1.name = "in1";
		in1.age = 50;
		foo(in1);
		System.out.println("Inner="+in1.toString());
	}
}
