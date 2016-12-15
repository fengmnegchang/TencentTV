package android.support.v4.view.verticalview;
///*
//2 * Copyright (C) 2011 The Android Open Source Project
//3 *
//4 * Licensed under the Apache License, Version 2.0 (the "License");
//5 * you may not use this file except in compliance with the License.
//6 * You may obtain a copy of the License at
//7 *
//8 *      http://www.apache.org/licenses/LICENSE-2.0
//9 *
//10 * Unless required by applicable law or agreed to in writing, software
//11 * distributed under the License is distributed on an "AS IS" BASIS,
//12 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//13 * See the License for the specific language governing permissions and
//14 * limitations under the License.
//15 */
//
//import android.database.DataSetObservable;
//
//16
//        17package android.support.v4.view;
//        18
//        19import android.database.DataSetObservable;
//        20import android.database.DataSetObserver;
//        21import android.os.Parcelable;
//        22import android.view.View;
//        23import android.view.ViewGroup;
//        24
//        25/**
// 26 * Base class providing the adapter to populate pages inside of
// 27 * a {@link ViewPager}.  You will most likely want to use a more
// 28 * specific implementation of this, such as
// 29 * {@link android.support.v4.app.FragmentPagerAdapter} or
// 30 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
// 31 *
// 32 * <p>When you implement a PagerAdapter, you must override the following methods
// 33 * at minimum:</p>
// 34 * <ul>
// 35 * <li>{@link #instantiateItem(ViewGroup, int)}</li>
// 36 * <li>{@link #destroyItem(ViewGroup, int, Object)}</li>
// 37 * <li>{@link #getCount()}</li>
// 38 * <li>{@link #isViewFromObject(View, Object)}</li>
// 39 * </ul>
// 40 *
// 41 * <p>PagerAdapter is more general than the adapters used for
// 42 * {@link android.widget.AdapterView AdapterViews}. Instead of providing a
// 43 * View recycling mechanism directly ViewPager uses callbacks to indicate the
// 44 * steps taken during an update. A PagerAdapter may implement a form of View
// 45 * recycling if desired or use a more sophisticated method of managing page
// 46 * Views such as Fragment transactions where each page is represented by its
// 47 * own Fragment.</p>
// 48 *
// 49 * <p>ViewPager associates each page with a key Object instead of working with
// 50 * Views directly. This key is used to track and uniquely identify a given page
// 51 * independent of its position in the adapter. A call to the PagerAdapter method
// 52 * {@link #startUpdate(ViewGroup)} indicates that the contents of the ViewPager
// 53 * are about to change. One or more calls to {@link #instantiateItem(ViewGroup, int)}
// 54 * and/or {@link #destroyItem(ViewGroup, int, Object)} will follow, and the end
// 55 * of an update will be signaled by a call to {@link #finishUpdate(ViewGroup)}.
// 56 * By the time {@link #finishUpdate(ViewGroup) finishUpdate} returns the views
// 57 * associated with the key objects returned by
// 58 * {@link #instantiateItem(ViewGroup, int) instantiateItem} should be added to
// 59 * the parent ViewGroup passed to these methods and the views associated with
// 60 * the keys passed to {@link #destroyItem(ViewGroup, int, Object) destroyItem}
// 61 * should be removed. The method {@link #isViewFromObject(View, Object)} identifies
// 62 * whether a page View is associated with a given key object.</p>
// 63 *
// 64 * <p>A very simple PagerAdapter may choose to use the page Views themselves
// 65 * as key objects, returning them from {@link #instantiateItem(ViewGroup, int)}
// 66 * after creation and adding them to the parent ViewGroup. A matching
// 67 * {@link #destroyItem(ViewGroup, int, Object)} implementation would remove the
// 68 * View from the parent ViewGroup and {@link #isViewFromObject(View, Object)}
// 69 * could be implemented as <code>return view == object;</code>.</p>
// 70 *
// 71 * <p>PagerAdapter supports data set changes. Data set changes must occur on the
// 72 * main thread and must end with a call to {@link #notifyDataSetChanged()} similar
// 73 * to AdapterView adapters derived from {@link android.widget.BaseAdapter}. A data
// 74 * set change may involve pages being added, removed, or changing position. The
// 75 * ViewPager will keep the current page active provided the adapter implements
// 76 * the method {@link #getItemPosition(Object)}.</p>
// 77 */
//        78public abstract class PagerAdapter {
//    79    private DataSetObservable mObservable = new DataSetObservable();
//    80
//            81    public static final int POSITION_UNCHANGED = -1;
//    82    public static final int POSITION_NONE = -2;
//    83
//            84    /**
//     85     * Return the number of views available.
//     86     */
//            87    public abstract int getCount();
//    88
//            89    /**
//     90     * Called when a change in the shown pages is going to start being made.
//     91     * @param container The containing View which is displaying this adapter's
//     92     * page views.
//     93     */
//            94    public void startUpdate(ViewGroup container) {
//        95        startUpdate((View) container);
//        96    }
//    97
//            98    /**
//     99     * Create the page for the given position.  The adapter is responsible
//     100     * for adding the view to the container given here, although it only
//     101     * must ensure this is done by the time it returns from
//     102     * {@link #finishUpdate(ViewGroup)}.
//     103     *
//     104     * @param container The containing View in which the page will be shown.
//     105     * @param position The page position to be instantiated.
//     106     * @return Returns an Object representing the new page.  This does not
//     107     * need to be a View, but can be some other container of the page.
//     108     */
//            109    public Object instantiateItem(ViewGroup container, int position) {
//        110        return instantiateItem((View) container, position);
//        111    }
//    112
//            113    /**
//     114     * Remove a page for the given position.  The adapter is responsible
//     115     * for removing the view from its container, although it only must ensure
//     116     * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
//     117     *
//     118     * @param container The containing View from which the page will be removed.
//     119     * @param position The page position to be removed.
//     120     * @param object The same object that was returned by
//     121     * {@link #instantiateItem(View, int)}.
//     122     */
//            123    public void destroyItem(ViewGroup container, int position, Object object) {
//        124        destroyItem((View) container, position, object);
//        125    }
//    126
//            127    /**
//     128     * Called to inform the adapter of which item is currently considered to
//     129     * be the "primary", that is the one show to the user as the current page.
//     130     *
//     131     * @param container The containing View from which the page will be removed.
//     132     * @param position The page position that is now the primary.
//     133     * @param object The same object that was returned by
//     134     * {@link #instantiateItem(View, int)}.
//     135     */
//            136    public void setPrimaryItem(ViewGroup container, int position, Object object) {
//        137        setPrimaryItem((View) container, position, object);
//        138    }
//    139
//            140    /**
//     141     * Called when the a change in the shown pages has been completed.  At this
//     142     * point you must ensure that all of the pages have actually been added or
//     143     * removed from the container as appropriate.
//     144     * @param container The containing View which is displaying this adapter's
//     145     * page views.
//     146     */
//            147    public void finishUpdate(ViewGroup container) {
//        148        finishUpdate((View) container);
//        149    }
//    150
//            151    /**
//     152     * Called when a change in the shown pages is going to start being made.
//     153     * @param container The containing View which is displaying this adapter's
//     154     * page views.
//     155     *
//     156     * @deprecated Use {@link #startUpdate(ViewGroup)}
//     157     */
//            158    public void startUpdate(View container) {
//        159    }
//    160
//            161    /**
//     162     * Create the page for the given position.  The adapter is responsible
//     163     * for adding the view to the container given here, although it only
//     164     * must ensure this is done by the time it returns from
//     165     * {@link #finishUpdate(ViewGroup)}.
//     166     *
//     167     * @param container The containing View in which the page will be shown.
//     168     * @param position The page position to be instantiated.
//     169     * @return Returns an Object representing the new page.  This does not
//     170     * need to be a View, but can be some other container of the page.
//     171     *
//     172     * @deprecated Use {@link #instantiateItem(ViewGroup, int)}
//     173     */
//            174    public Object instantiateItem(View container, int position) {
//        175        throw new UnsupportedOperationException(
//                176                "Required method instantiateItem was not overridden");
//        177    }
//    178
//            179    /**
//     180     * Remove a page for the given position.  The adapter is responsible
//     181     * for removing the view from its container, although it only must ensure
//     182     * this is done by the time it returns from {@link #finishUpdate(View)}.
//     183     *
//     184     * @param container The containing View from which the page will be removed.
//     185     * @param position The page position to be removed.
//     186     * @param object The same object that was returned by
//     187     * {@link #instantiateItem(View, int)}.
//     188     *
//     189     * @deprecated Use {@link #destroyItem(ViewGroup, int, Object)}
//     190     */
//            191    public void destroyItem(View container, int position, Object object) {
//        192        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
//        193    }
//    194
//            195    /**
//     196     * Called to inform the adapter of which item is currently considered to
//     197     * be the "primary", that is the one show to the user as the current page.
//     198     *
//     199     * @param container The containing View from which the page will be removed.
//     200     * @param position The page position that is now the primary.
//     201     * @param object The same object that was returned by
//     202     * {@link #instantiateItem(View, int)}.
//     203     *
//     204     * @deprecated Use {@link #setPrimaryItem(ViewGroup, int, Object)}
//     205     */
//            206    public void setPrimaryItem(View container, int position, Object object) {
//        207    }
//    208
//            209    /**
//     210     * Called when the a change in the shown pages has been completed.  At this
//     211     * point you must ensure that all of the pages have actually been added or
//     212     * removed from the container as appropriate.
//     213     * @param container The containing View which is displaying this adapter's
//     214     * page views.
//     215     *
//     216     * @deprecated Use {@link #finishUpdate(ViewGroup)}
//     217     */
//            218    public void finishUpdate(View container) {
//        219    }
//    220
//            221    /**
//     222     * Determines whether a page View is associated with a specific key object
//     223     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
//     224     * required for a PagerAdapter to function properly.
//     225     *
//     226     * @param view Page View to check for association with <code>object</code>
//     227     * @param object Object to check for association with <code>view</code>
//     228     * @return true if <code>view</code> is associated with the key object <code>object</code>
//     229     */
//            230    public abstract boolean isViewFromObject(View view, Object object);
//    231
//            232    /**
//     233     * Save any instance state associated with this adapter and its pages that should be
//     234     * restored if the current UI state needs to be reconstructed.
//     235     *
//     236     * @return Saved state for this adapter
//     237     */
//            238    public Parcelable saveState() {
//        239        return null;
//        240    }
//    241
//            242    /**
//     243     * Restore any instance state associated with this adapter and its pages
//     244     * that was previously saved by {@link #saveState()}.
//     245     *
//     246     * @param state State previously saved by a call to {@link #saveState()}
//     247     * @param loader A ClassLoader that should be used to instantiate any restored objects
//     248     */
//            249    public void restoreState(Parcelable state, ClassLoader loader) {
//        250    }
//    251
//            252    /**
//     253     * Called when the host view is attempting to determine if an item's position
//     254     * has changed. Returns {@link #POSITION_UNCHANGED} if the position of the given
//     255     * item has not changed or {@link #POSITION_NONE} if the item is no longer present
//     256     * in the adapter.
//     257     *
//     258     * <p>The default implementation assumes that items will never
//     259     * change position and always returns {@link #POSITION_UNCHANGED}.
//     260     *
//     261     * @param object Object representing an item, previously returned by a call to
//     262     *               {@link #instantiateItem(View, int)}.
//     263     * @return object's new position index from [0, {@link #getCount()}),
//     264     *         {@link #POSITION_UNCHANGED} if the object's position has not changed,
//     265     *         or {@link #POSITION_NONE} if the item is no longer present.
//     266     */
//            267    public int getItemPosition(Object object) {
//        268        return POSITION_UNCHANGED;
//        269    }
//    270
//            271    /**
//     272     * This method should be called by the application if the data backing this adapter has changed
//     273     * and associated views should update.
//     274     */
//            275    public void notifyDataSetChanged() {
//        276        mObservable.notifyChanged();
//        277    }
//    278
//            279    void registerDataSetObserver(DataSetObserver observer) {
//        280        mObservable.registerObserver(observer);
//        281    }
//    282
//            283    void unregisterDataSetObserver(DataSetObserver observer) {
//        284        mObservable.unregisterObserver(observer);
//        285    }
//    286
//            287    /**
//     288     * This method may be called by the ViewPager to obtain a title string
//     289     * to describe the specified page. This method may return null
//     290     * indicating no title for this page. The default implementation returns
//     291     * null.
//     292     *
//     293     * @param position The position of the title requested
//     294     * @return A title for the requested page
//     295     */
//            296    public CharSequence getPageTitle(int position) {
//        297        return null;
//        298    }
//    299
//            300    /**
//     301     * Returns the proportional width of a given page as a percentage of the
//     302     * ViewPager's measured width from (0.f-1.f]
//     303     *
//     304     * @param position The position of the page requested
//     305     * @return Proportional width for the given page position
//     306     */
//            307    public float getPageWidth(int position) {
//        308        return 1.f;
//        309    }
//    310}
//311