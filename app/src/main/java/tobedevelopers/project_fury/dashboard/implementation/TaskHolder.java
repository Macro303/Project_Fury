package tobedevelopers.project_fury.dashboard.implementation;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 12/09/2016.
 */
public class TaskHolder{

	private ArrayList< Pair< Task, Column > > pairList;

	public TaskHolder(){
		pairList = new ArrayList<>();
	}

	public void addPair( Task task, Column column ){
		pairList.add( new Pair<>( task, column ) );
	}

	public ArrayList< Pair< Task, Column > > getPairList(){
		Collections.sort( pairList, new PairComparator() );
		return pairList;
	}

	private class PairComparator implements Comparator< Pair< Task, Column > >{
		@Override
		public int compare( Pair< Task, Column > p1, Pair< Task, Column > p2 ){
			return p1.first.compareTo( p2.first );
		}
	}
}
