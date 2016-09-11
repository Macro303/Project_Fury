package tobedevelopers.project_fury.project_info.implementation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.project_info.ColumnRecyclerAdapter;
import tobedevelopers.project_fury.project_info.ProjectInfoContract;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;
import tobedevelopers.project_fury.runnable_param.Runnable2Param;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class ProjectInfoView extends BaseView implements ProjectInfoContract.View, ProjectInfoContract.Navigation{

	@Bind( R.id.projectInfoActivity_projectNameEditText )
	TextInputEditText mProjectNameEditText;
	@Bind( R.id.projectInfoActivity_projectDescriptionEditText )
	TextInputEditText mProjectDescriptionEditText;
	@Bind( R.id.projectInfoActivity_editProjectButton )
	Button mEditProjectButton;
	@Bind( R.id.projectInfoActivity_saveProjectButton )
	Button mSaveProjectButton;
	@Bind( R.id.projectInfoActivity_deleteProjectButton )
	Button mDeleteProjectButton;
	@Bind( R.id.projectInfoActivity_addColumnButton )
	Button mAddColumnButton;
	@Bind( R.id.projectInfoActivity_deleteColumnButton )
	Button mDeleteColumnButton;
//	@Bind( R.id.projectInfoActivity_columnNamesList )
//	ListView mColumnNamesList;

	private ProjectInfoContract.Presenter presenter;

	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.ItemDecoration itemDecoration;
	private ColumnRecyclerAdapter columnRecyclerAdapter;

	private String columnName;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_projectInfo ) );
		setContentView( R.layout.activity_project_info );
		super.onCreate( savedInstanceState );

		presenter = new ProjectInfoPresenter( this, this );

		ButterKnife.bind( this );

		//Toolbar Config
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );

		//Spinner Config
		addItemsToFields();

		//List Config
		presenter.loadColumns();

		//UI References
		mRecyclerView = ( RecyclerView ) findViewById( R.id.projectInfoActivity_columnNamesList );

		//Recycler Config
		mRecyclerView.setHasFixedSize( true );
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
		mRecyclerView.setAdapter( mAdapter );

		itemDecoration = new DividerItemDecoration( this, DividerItemDecoration.VERTICAL_LIST );
		mRecyclerView.addItemDecoration( itemDecoration );

//		ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback( columnRecyclerAdapter );
//		ItemTouchHelper touchHelper = new ItemTouchHelper( callback );
//		touchHelper.attachToRecyclerView( mRecyclerView );
	}

	private void addItemsToFields(){
		Project selectedProject = Model.getSelectedProject();
		mProjectNameEditText.setText( selectedProject.getName() );
		String description = selectedProject.getDescription();
		if( description.equals( "null" ) )
			description = "";
		mProjectDescriptionEditText.setText( description );
	}

	@Override
	public void fillColumnList( Column[] columns ){
		mAdapter = new ColumnRecyclerAdapter( this, columns );
		mRecyclerView.setAdapter( mAdapter );

//		columnRecyclerAdapter = new ColumnRecyclerAdapter( ProjectInfoView.this, columns );

//		CustomArrayAdapter adapter = new CustomArrayAdapter( this, R.layout.list_item_columns, columns );
//		mColumnNamesList.setAdapter( adapter );
//		mColumnNamesList.addHeaderView( getLayoutInflater().inflate( R.layout.list_header_columns, mColumnNamesList, false ), null, false );
	}

	//Button Listener
	@OnClick( { R.id.projectInfoActivity_editProjectButton, R.id.projectInfoActivity_saveProjectButton, R.id.projectInfoActivity_deleteProjectButton, R.id.projectInfoActivity_addColumnButton, R.id.projectInfoActivity_deleteColumnButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.projectInfoActivity_editProjectButton:
//				ToastLog.makeDebug( this, "Edit Project", Toast.LENGTH_SHORT );
				presenter.userSelectEditProject();
				break;
			case R.id.projectInfoActivity_saveProjectButton:
//				ToastLog.makeDebug( this, "Save Project", Toast.LENGTH_SHORT );
				presenter.userSelectSaveProject();
				break;
			case R.id.projectInfoActivity_deleteProjectButton:
//				ToastLog.makeDebug( this, "Delete Project", Toast.LENGTH_SHORT );
				alertDeleteProject();
				break;
			case R.id.projectInfoActivity_addColumnButton:
//				ToastLog.makeDebug( this, "Add Column", Toast.LENGTH_SHORT );
				alertAddColumn();
				break;
			case R.id.projectInfoActivity_deleteColumnButton:
//				ToastLog.makeDebug( this, "Delete Column", Toast.LENGTH_SHORT );
				alertDeleteColumn();
				break;
			default:
				ToastLog.makeError( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}

	//Text Changed Listeners
	@OnTextChanged( value = { R.id.projectInfoActivity_projectNameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangeProjectNameEditText( Editable editable ){
		presenter.userEnterProjectName( editable.toString() );
	}

	@OnTextChanged( value = { R.id.projectInfoActivity_projectDescriptionEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedProjectDescriptionEditText( Editable editable ){
		presenter.userEnterProjectDescription( editable.toString() );
	}

	private void alertDeleteProject(){
		AlertDialog.Builder builder = new AlertDialog.Builder( this );

		builder.setMessage( R.string.dialog_deleteAlertInstructions_project )
			.setTitle( R.string.dialog_deleteAlertTitle_project );
		builder.setPositiveButton( R.string.button_dialogDelete, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				presenter.userSelectDeleteProject();
			}
		} );

		builder.setNegativeButton( R.string.button_dialogCancel, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				dialogInterface.cancel();
			}
		} );

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	private void alertAddColumn(){
		AlertDialog.Builder builder = new AlertDialog.Builder( this );
		builder.setTitle( R.string.dialog_addAlertTitle_column );

		final TextInputEditText addColumnNameEditText = new TextInputEditText( this );
		addColumnNameEditText.setHint( R.string.hint_columnName );
		addColumnNameEditText.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS );

		builder.setView( addColumnNameEditText );

		addColumnNameEditText.addTextChangedListener( new TextWatcher(){
			@Override
			public void beforeTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void onTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void afterTextChanged( Editable editable ){
				if( editable.toString().length() >= 3 && editable.toString().length() <= 8 ){
					columnName = editable.toString();
				}else{
					if( editable.toString().length() < 3 ){
						addColumnNameEditText.setError( String.format( getResources().getQuantityString( R.plurals.error_minCharacters, 3 ), 3 ) );
					}else if( editable.toString().length() > 8 ){
						addColumnNameEditText.setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 8 ), 8 ) );
					}
				}
			}
		} );

		builder.setPositiveButton( "Create", new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				presenter.userSelectAddColumn( columnName );
				saveProjectDescription();
			}
		} );

		builder.setNegativeButton( R.string.button_dialogCancel, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				dialogInterface.cancel();
				saveProjectDescription();
			}
		} );

//		builder.show();
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	private void alertDeleteColumn(){

	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ){
		switch( item.getItemId() ){
			case android.R.id.home:
				presenter.userSelectBack();
				return true;
		}

		return super.onOptionsItemSelected( item );
	}

	@Override
	public void navigateToPrevious(){
		finish();
	}

	@Override
	public void editProjectDescription(){
		runOnUiThread( new Runnable2Param< TextInputEditText, TextInputEditText >( mProjectNameEditText, mProjectDescriptionEditText ){
			@Override
			public void run(){
				mEditProjectButton.setVisibility( View.GONE );
				getParam1().setFocusable( true );
				getParam1().setFocusableInTouchMode( true );
				getParam1().setClickable( true );
				getParam1().setCursorVisible( true );
				getParam2().setFocusable( true );
				getParam2().setFocusableInTouchMode( true );
				getParam2().setClickable( true );
				getParam2().setCursorVisible( true );
				mSaveProjectButton.setVisibility( View.VISIBLE );
				mAddColumnButton.setEnabled( true );
				mDeleteColumnButton.setEnabled( true );
			}
		} );
	}

	@Override
	public void saveProjectDescription(){
		runOnUiThread( new Runnable2Param< TextInputEditText, TextInputEditText >( mProjectNameEditText, mProjectDescriptionEditText ){
			@Override
			public void run(){
				mSaveProjectButton.setVisibility( View.GONE );
				getParam1().setFocusable( false );
				getParam1().setFocusableInTouchMode( false );
				getParam1().setClickable( false );
				getParam1().setCursorVisible( false );
				getParam2().setFocusable( false );
				getParam2().setFocusableInTouchMode( false );
				getParam2().setClickable( false );
				getParam2().setCursorVisible( false );
				mEditProjectButton.setVisibility( View.VISIBLE );
				mAddColumnButton.setEnabled( false );
				mDeleteColumnButton.setEnabled( false );
			}
		} );
	}

	@Override
	public void addColumnName(){
		presenter.loadColumns();
	}

	@Override
	public void projectUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< ProjectInfoView >( this ){
			@Override
			public void run(){
				ToastLog.makeInfo( getParam1(), String.format( getString( R.string.error_inProgress ), "Project description saving" ), Toast.LENGTH_LONG );
			}
		} );
	}

	@Override
	public void setProjectNameUnderValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mProjectNameEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_minCharacters, 3 ), 3 ) );
			}
		} );
	}

	@Override
	public void setProjectNameOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mProjectNameEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 20 ), 20 ) );
			}
		} );
	}

	@Override
	public void setProjectDescriptionOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mProjectDescriptionEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 128 ), 128 ) );
			}
		} );
	}

	@Override
	public void enableSave(){
		runOnUiThread( new Runnable1Param< Button >( mSaveProjectButton ){
			@Override
			public void run(){
				getParam1().setEnabled( true );
			}
		} );
	}

	@Override
	public void disableSave(){
		runOnUiThread( new Runnable1Param< Button >( mSaveProjectButton ){
			@Override
			public void run(){
				getParam1().setEnabled( false );
			}
		} );
	}

	@Override
	public void noInternetAccessValidation(){
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG );
	}

	@Override
	public void defaultErrorMessage(){
		ToastLog.makeWarn( this, getString( R.string.error_defaultError ), Toast.LENGTH_LONG );
		//mProjectDescriptionEditText.getEditableText().clear();
	}

	@Override
	public void setInvalidUserValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mProjectNameEditText ){
			@Override
			public void run(){
				getParam1().setError( getString( R.string.error_alreadyExists, "Project name" ) );
			}
		} );
	}

	public class DividerItemDecoration extends RecyclerView.ItemDecoration{

//		private final int[] ATTRS = new int[]{android.R.attr.listDivider};
//
//		private Drawable mDivider;
//
//		/**
//		 * Default divider will be used
//		 */
//		public DividerItemDecoration(Context context) {
//			final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
//			mDivider = styledAttributes.getDrawable(0);
//			styledAttributes.recycle();
//		}
//
//		@Override
//		public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//			int left = parent.getPaddingLeft();
//			int right = parent.getWidth() - parent.getPaddingRight();
//
//			int childCount = parent.getChildCount();
//			for (int i = 0; i < childCount; i++) {
//				View child = parent.getChildAt(i);
//
//				RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//
//				int top = child.getBottom() + params.bottomMargin;
//				int bottom = top + mDivider.getIntrinsicHeight();
//
//				mDivider.setBounds(left, top, right, bottom);
//				mDivider.draw(c);
//			}
//		}

		public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
		public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
		private final int[] ATTRS = new int[]{
			android.R.attr.listDivider
		};
		private Drawable mDivider;

		private int mOrientation;

		public DividerItemDecoration( Context context, int orientation ){
			final TypedArray a = context.obtainStyledAttributes( ATTRS );
			mDivider = a.getDrawable( 0 );
			a.recycle();
			setOrientation( orientation );
		}

		public void setOrientation( int orientation ){
			if( orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST ){
				throw new IllegalArgumentException( "invalid orientation" );
			}
			mOrientation = orientation;
		}

		@Override
		public void onDraw( Canvas c, RecyclerView parent ){
			if( mOrientation == VERTICAL_LIST ){
				drawVertical( c, parent );
			}else{
				drawHorizontal( c, parent );
			}
		}

		public void drawVertical( Canvas c, RecyclerView parent ){
			final int left = parent.getPaddingLeft();
			final int right = parent.getWidth() - parent.getPaddingRight();

			final int childCount = parent.getChildCount();
			for( int i = 0; i < childCount; i++ ){
				final View child = parent.getChildAt( i );
				final RecyclerView.LayoutParams params = ( RecyclerView.LayoutParams ) child.getLayoutParams();
				final int top = child.getBottom() + params.bottomMargin;
				final int bottom = top + mDivider.getIntrinsicHeight();
				mDivider.setBounds( left, top, right, bottom );
				mDivider.draw( c );
			}
		}

		public void drawHorizontal( Canvas c, RecyclerView parent ){
			final int top = parent.getPaddingTop();
			final int bottom = parent.getHeight() - parent.getPaddingBottom();

			final int childCount = parent.getChildCount();
			for( int i = 0; i < childCount; i++ ){
				final View child = parent.getChildAt( i );
				final RecyclerView.LayoutParams params = ( RecyclerView.LayoutParams ) child.getLayoutParams();
				final int left = child.getRight() + params.rightMargin;
				final int right = left + mDivider.getIntrinsicHeight();
				mDivider.setBounds( left, top, right, bottom );
				mDivider.draw( c );
			}
		}

		@Override
		public void getItemOffsets( Rect outRect, int itemPosition, RecyclerView parent ){
			if( mOrientation == VERTICAL_LIST ){
				outRect.set( 0, 0, 0, mDivider.getIntrinsicHeight() );
			}else{
				outRect.set( 0, 0, mDivider.getIntrinsicWidth(), 0 );
			}
		}
	}
//
//	private class CustomArrayAdapter extends ArrayAdapter< Column >{
//		private LayoutInflater inflater = null;
//		private Column[] columns;
//
//		public CustomArrayAdapter( Context context, int id, Column[] columns ){
//			super( context, id );
//			this.columns = columns;
//		}
//
//		@Override
//		public int getCount(){
//			return columns.length;
//		}
//
//		@Override
//		public Column getItem( int position ){
//			return columns[ position ];
//		}
//
//		@Override
//		public View getView( int position, View convertView, ViewGroup parent ){
//			View view = convertView;
//			if( view == null ){
//				inflater = LayoutInflater.from( getApplicationContext() );
//				view = inflater.inflate( R.layout.list_item_columns, null );
//			}
//			Column column = getItem( position );
//			if( column != null ){
//				TextView mTextView = ( TextView ) view.findViewById( R.id.listItem_columnName );
//				mTextView.setText( column.getName() );
//			}
//
//			return view;
//		}
//
//		@Override
//		public boolean hasStableIds(){
//			return true;
//		}
//	}
}
