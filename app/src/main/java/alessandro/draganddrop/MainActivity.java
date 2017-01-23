package alessandro.draganddrop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import alessandro.draganddrop.adapter.MyAdapterRecyclerView;
import alessandro.draganddrop.adapter.MyItemTouchHelperCallback;
import alessandro.draganddrop.interfaces.CallbackItemTouch;
import alessandro.draganddrop.model.Item;

public class MainActivity extends AppCompatActivity implements CallbackItemTouch {

    private RecyclerView mRecyclerView; // RecyclerVIew
    private MyAdapterRecyclerView myAdapterRecyclerView; //The Adapter for RecyclerVIew
    private List<Item> mList; // My List the object 'Item'.

    // Array images
    private int images[] = new int[]{
            R.drawable.android,
            R.drawable.batman,
            R.drawable.deadpool,
            R.drawable.gambit,
            R.drawable.hulk,
            R.drawable.mario,
            R.drawable.wolverine,
            R.drawable.daredevil
    };

    // Array names
    private String names[] = new String[]{
            "Android",
            "Batman",
            "DeadPool",
            "Gambit",
            "Hulk",
            "Mario",
            "Wolverine",
            "Daredevil"
    };

    // Description text
    private String textDescription = "Subtitle description,lorem ipsum text generic etc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        initList(); //call method

    }

    /**
     * Add data to the List
     */
    private void initList(){
        // Adds data to List of Objects Item
        mList = new ArrayList<>();
        for (int i = 0; i<names.length;i++){
            mList.add(new Item(images[i],names[i],textDescription));
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set LayoutManager in the RecyclerView
        myAdapterRecyclerView = new MyAdapterRecyclerView(mList); // Create Instance of MyAdapterRecyclerView
        mRecyclerView.setAdapter(myAdapterRecyclerView); // Set Adapter for RecyclerView
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(this);// create MyItemTouchHelperCallback
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback); // Create ItemTouchHelper and pass with parameter the MyItemTouchHelperCallback
        touchHelper.attachToRecyclerView(mRecyclerView); // Attach ItemTouchHelper to RecyclerView
    }

    @Override
    public void itemTouchOnMove(int oldPosition, int newPosition) {
        mList.add(newPosition,mList.remove(oldPosition));// change position
        myAdapterRecyclerView.notifyItemMoved(oldPosition, newPosition); //notifies changes in adapter, in this case use the notifyItemMoved
    }
}
