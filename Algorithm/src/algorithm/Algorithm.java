package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Algorithm {
	
	private static void reculsion(List<String> arr, List<String> result, int n, int r,List<String> res) {

		if (r == 0) {

			
			//System.out.println(result.toString());
			for(int i=0;i<result.size();i++) {
				String s="";
				for(int j=0;j<result.size();j++) {
					
					s+=result.get(j);
					
				}
				res.add(s);
			}
			return;
		}

		for (int i = 0; i < n; i++) {

			result.add(arr.remove(i));//첫번째 숫자를 구함
			reculsion(arr, result, n - 1, r - 1,res);//다음 숫자부터는 재귀함수로
			arr.add(i, result.remove(result.size() - 1));
		}
	}

	public static String returnMax(List<String> resarr,List<String> arr) {
		
		
		
		return null;
	}

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("입력할 숫자의 개수를 입력하게요");
		int cnt=sc.nextInt();
		
		List<String> arr = new ArrayList<String>();//원래 배열
		List<String> resarr = new ArrayList<String>();//조합을 한 이후의 리스트
		List<String> res = new ArrayList<String>();//조합을 한 이후의 리스트
		for (int i = 0; i < cnt; i++) {
			System.out.println("정수를 입력하세요");
			arr.add(sc.next());

			if (Integer.parseInt(arr.get(i)) < 0 || Integer.parseInt(arr.get(i)) > 1000) {// 범위 제한
				System.out.println("다시 입력하세요");
				i = i - 1;// 넣었던거 취소
			}
		}
		reculsion(arr,resarr,cnt,cnt,res);
		
		//System.out.println(res);
		Set<String> set=new HashSet<String>();
		Set<Integer> set2=new HashSet<Integer>();
		List<Integer> intlist = new ArrayList<Integer>();//원래 배열
		
		for(int i=0;i<res.size();i++) {
			set.add(res.get(i));
			set2.add(Integer.parseInt(res.get(i)));
		}
		//System.out.println(set2);
		
		Iterator<Integer> iter = set2.iterator(); 
		while(iter.hasNext()) { 
			intlist.add(iter.next());
			
		}
		
		Collections.sort(intlist);
		System.out.println("제일 큰 수 조합 :"+intlist.get(intlist.size()-1));
		

		
		
		
		

	}

}
