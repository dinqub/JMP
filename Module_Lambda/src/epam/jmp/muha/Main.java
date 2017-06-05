package epam.jmp.muha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main 
{

	public static void main(String[] args) 
	{
		Consumer print = System.out::println;
		Function<Point, NewPoint> newPoint = e -> new NewPoint(e.getX(), e.getY());
		Predicate<NewPoint> filterNewPoint = elem -> elem.getA()>=3 && elem.getB()>=3;
		
		BiFunction<Long,NewPoint,Long> sumary = (sum, e) -> sum += e.getA() + e.getB();
		BinaryOperator<Long> result = (sum1, sum2) -> sum1 + sum2;
		
		BiFunction<Long,NewPoint,Long> multiplication = (m1, m2) -> m1 *= m2.getA() * m2.getB();
		BinaryOperator<Long> multiplicationResult = (m1, m2) -> m1 * m2;
		
		List<Point> pointList = new ArrayList<Point>();
		Random random = new Random();
		for (int i=1; i<=10; ++i) 
		{
			pointList.add(new Point(random.nextInt(i),random.nextInt(i)));
		}		
		pointList.forEach(print);
		
		System.out.println("Filted list:");
		
		pointList.stream()
		.map(newPoint)
		.filter(filterNewPoint)
		.distinct()
		.forEach(print);
		Long sum = pointList.stream()
				.map(newPoint)
				.filter(filterNewPoint)
				.distinct().reduce(0L, sumary, result);		
		System.out.println("Sum coordinates of all filtred elements: "+sum);
		
		Long mult = pointList.stream()
				.map(newPoint)
				.filter(filterNewPoint)
				.distinct().reduce(1L, multiplication, multiplicationResult);		
		System.out.println("Multiplication coordinates of all filtred elements: "+mult);
		
	}
}
