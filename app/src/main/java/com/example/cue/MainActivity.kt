package com.example.cue

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow



//import sun.jvm.hotspot.utilities.IntArray


class MainActivity : AppCompatActivity() {
    private var mCoverFlow: FeatureCoverFlow? = null
    private var mAdapter: CoverFlowAdapter? = null
    private var mTitle: TextSwitcher? = null
    private val mData: ArrayList<GameEntity> = ArrayList(0)

    var sliderDotspanel: LinearLayout? = null
    private var dotscount = 0
    private lateinit var dots: Array<ImageView?>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.cue.R.layout.activity_main)



        mData.add(GameEntity(com.example.cue.R.drawable.image_1, com.example.cue.R.string.title1))
        mData.add(GameEntity(com.example.cue.R.drawable.image_2, com.example.cue.R.string.title2))
        mData.add(GameEntity(com.example.cue.R.drawable.image_3, com.example.cue.R.string.title3))
        mData.add(GameEntity(com.example.cue.R.drawable.image_4, com.example.cue.R.string.title4))

        mTitle = findViewById<View>(com.example.cue.R.id.title) as TextSwitcher
        mTitle!!.setFactory(ViewSwitcher.ViewFactory {
            val inflater = LayoutInflater.from(this@MainActivity)
            inflater.inflate(com.example.cue.R.layout.item_title, null) as TextView
        })


        val `in` = AnimationUtils.loadAnimation(this, com.example.cue.R.anim.slide_in_top)
        val out = AnimationUtils.loadAnimation(this, com.example.cue.R.anim.slide_out_bottom)
        mTitle!!.inAnimation = `in`
        mTitle!!.outAnimation = out

        mAdapter = CoverFlowAdapter(this)
        mAdapter!!.setData(mData)
        mCoverFlow = findViewById<FeatureCoverFlow>(com.example.cue.R.id.coverflow)
        mCoverFlow!!.adapter = mAdapter

        var sliderDotspanel = findViewById<LinearLayout>(R.id.SliderDots);
        dotscount = mCoverFlow!!.count
        dots = arrayOfNulls(dotscount)

        for (i in 0 until dotscount) {
            dots!![i]= ImageView(this)
            dots!![i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.nonactive_dots
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel.addView(dots!![i], params)
        }


        dots[0]?.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dots));





        /*mCoverFlow!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            //CoverFlow item clicked
            var pos: Int=0
            if(position >4 ){
                pos= position-4*(position/4)-1
            }
            else{
                pos=position-1
            }
            Toast.makeText(this@MainActivity,
                resources.getString(mData[pos].titleResId),
                Toast.LENGTH_SHORT).show();
        }*/

        mCoverFlow!!.setOnScrollPositionListener(object :
            FeatureCoverFlow.OnScrollPositionListener {
            override fun onScrolledToPosition(position: Int) { // CoverFlow stopped to position
                var pos: Int = 0
                /*if(position >4 ){
                     pos= position-4*(position/4)-1
                }
                else{
                    pos=position
                }*/
                var toast : Toast = Toast.makeText(this@MainActivity,
                    resources.getString(mData[position].titleResId),
                    Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,400)
                toast.show()

                //mTitle?.setText(resources.getString(mData[position].titleResId));


                    for (i in 0 until dotscount) {
                        dots[i]!!.setImageDrawable(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.nonactive_dots
                            )
                        )
                    }
                    dots[position]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.active_dots
                        )
                    )
                }


            override fun onScrolling() { // CoverFlow began scrolling
                mTitle?.setText("");
            }
        })




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(com.example.cue.R.menu.menu_coverflow_activity, menu)
        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()
        return if (id == com.example.cue.R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

}
