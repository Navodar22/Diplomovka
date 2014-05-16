package compactTFTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DinicAlgorithm
{
	private Map[] v;
	int n;

	public DinicAlgorithm()
	{
		v = new HashMap[1000];
		n = 100;

		for(int i = 0; i < 1000; i++)
		{
			v[i] = new HashMap<Integer, Integer>();
		}
	}

/*
int bfs(int s, int t) {
	queue<pair<int, int> > q;
	int visit[n];
	fill(visit,visit+n,0);
	q.push(make_pair(s, 0));
	while (!q.empty()) {
		int tmp = q.front().first;
		int level = q.front().second;
		if (tmp == t) return level;
		q.pop();
		if (!visit[tmp]) {
			visit[tmp] = 1;
			for (map<int, int>::iterator it = v[tmp].begin();
					it != v[tmp].end(); it++)
			{
				if (it->second > 0 && !visit[it->first])
					q.push(make_pair(it->first, level + 1));
			}
		}
	}
	return 0;
}
*/
	public int bfs(int s, int t)
	{
		Queue< HashMap<Integer, Integer> > queue = new LinkedList< HashMap<Integer, Integer> >();
		int[] visit = new int[n];

		HashMap m = new HashMap<Integer, Integer>();
		m.put( new Integer(s), new Integer(0) );

		queue.add( m );

		while( queue.isEmpty() )
		{	
			HashMap<Integer, Integer> map = queue.element();
			Iterator iterator = map.entrySet().iterator();
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			int tmp = ((Integer)mapEntry.getKey()).intValue();
			int level = ((Integer)mapEntry.getValue()).intValue();

			if( tmp == t ) return level;

			queue.remove();

			if( visit[tmp] == 0 )
			{
				visit[tmp] = 1;

				iterator = v[tmp].entrySet().iterator();

				while( iterator.hasNext() )
				{
					mapEntry = (Map.Entry) iterator.next();
					int first = ((Integer)mapEntry.getKey()).intValue();
					int second = ((Integer)mapEntry.getValue()).intValue();
					
					if( second > 0 && visit[first] == 0 )
					{
						queue.add( new HashMap<Integer, Integer>( new Integer(first), new Integer(level + 1)) );
					}
				}
			}
		}

		return 0;
	}

/*
int dfs(int s, int t, int lv, vector<int> *cf) {
	int tmp;
	if (s == t) {
		int min = INT_MAX;
		for (vector<int>::iterator it = cf->begin(); it != cf->end(); it++)
			if (min > *it) min = *it;
		return min;
	}
	if (lv == 0) return 0;
	for (iter it = v[s].begin(); it != v[s].end(); it++) {
		cf->push_back(it->second);
		if (it->second > 0 && (tmp = dfs(it->first, t, lv - 1, cf))) {
			it->second -= tmp;
			if (v[it->first].find(s) != v[it->first].end()) v[it->first].insert(
					make_pair(s, tmp));
			else v[it->first][s] -= tmp;
			return tmp;
		}
		cf->pop_back();
	}
	return 0;
}
*/

	public int dfs(int s, int t, int lv, ArrayList cf)
	{
		int tmp = 0;

		if( s == t )
		{
			int min = Integer.MAX_VALUE;

			for(int i = 0; i < cf.size(); i++)
			{
				int n = ( (Integer)cf.get(i) ).intValue();

				if( min > n )
				{
					min = n;
				}
			}

			return min;
		}

		if( lv == 0 )
		{
			return 0;
		}

		Iterator iterator = v[s].entrySet().iterator();

		while( iterator.hasNext() )
		{
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			int first = ((Integer)mapEntry.getKey()).intValue();
			int second = ((Integer)mapEntry.getValue()).intValue();

			cf.add( new Integer(second) );

			if( second > 0 && (tmp = dfs(first, t, lv - 1, cf)) != 0 )
			{
				second -= tmp;	// !!!
				mapEntry.setValue(second);

				//Iterator iterator2 = v[tmp].entrySet().iterator();
				//Map.Entry mapEntry2 = (Map.Entry) iterator2.next();

				Integer obj = (Integer)(v[first].get( new Integer(s) ) );

				if( obj != null )
				{
					v[first].put( new Integer(s), new Integer(tmp) );
				}
				else
				{
					v[first].put(obj, obj.intValue() - tmp);
				}

				return tmp;
			}

			cf.remove(0);
		}

		return 0;
	}

	public int augment(int s, int t, int lv)
	{
		int sum = 0;
		int tmp = 0;

		while( (tmp = dfs(s, t, lv, new ArrayList() ) ) != 0 )
		{
			sum += tmp;
		}

		return sum;
	}

	public int maxflow(int s, int t)
	{
		int sum = 0;
		int level;

		while( (level = bfs(s, t) ) != 0 )
		{
			System.out.println(level);
			sum += augment(s, t, level);
		}

		return sum;
	}

	public void test()
	{
		int s;
		int t;

		s = 1;
		t = 18;

		n = 20;

		v[0].put(1, 2);
		v[0].put(3, 4);
		v[0].put(5, 4);
		v[0].put(7, 2);
		v[0].put(9, 2);
		v[0].put(11, 2);
		v[0].put(13, 4);
		v[0].put(15, 2);
		v[0].put(17, 2);
	
		v[2].put(18, 1);
		v[4].put(18, 1);
		v[6].put(18, 1);
		v[8].put(18, 1);
		v[10].put(18, 6);
		v[12].put(18, 1);
		v[14].put(18, 1);
		v[16].put(18, 6);
	
		
		v[2].put(3, 30);
		v[4].put(5, 30);
		v[6].put(7, 30);
		v[8].put(9, 30);
		v[10].put(11, 30);
		v[12].put(13, 30);
		v[14].put(15, 30);
		v[16].put(17, 30);
	
	
		v[1].put(2, 30);
		v[1].put(4, 30);
		v[3].put(6, 30);
		v[5].put(8, 30);
		v[7].put(10, 30);
		v[9].put(10, 30);
		v[11].put(12, 30);
		v[11].put(14, 30);
		v[13].put(16, 30);
		v[15].put(16, 30);
		v[3].put(8, 30); 

		
		System.out.println( maxflow(s, t) );
	}

	public static void main(String[] args)
	{
		DinicAlgorithm flow = new DinicAlgorithm();
		flow.test();
	}
}