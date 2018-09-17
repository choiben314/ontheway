package itemp2p.hackmit.org.user

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.service.carrier.CarrierIdentifier
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_choose_location.*
import org.jetbrains.anko.startActivityForResult


class ChooseLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private var location: LatLng? = null
        set(value) {
            field = value
            if (value != null) setResult(value)
        }

    private fun setResult(location: LatLng) {
        val i = Intent()
        i.putExtra(LOCATION_EXTRA, location)
        setResult(Activity.RESULT_OK, i)
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_location)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        doneBtn.setOnClickListener {
            finish()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    ChooseLocationActivity.PERMISSION_REQUEST_CODE)
        } else tryGetLocation()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE)
            permissions.withIndex().forEach {
                if (it.value == android.Manifest.permission.ACCESS_FINE_LOCATION && grantResults[it.index] == PackageManager.PERMISSION_GRANTED) {
                    tryGetLocation()
                    return
                }
            }
    }

    @SuppressLint("MissingPermission")
    private fun tryGetLocation() {
        mMap.isMyLocationEnabled = true
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    println(location)
                    onReceivedUserLocation(location)
                }
    }

    private fun onReceivedUserLocation(userLocation: Location?) {

        location = if (userLocation != null) {
            LatLng(userLocation.latitude, userLocation.longitude)
        } else {
            val boston = LatLng(42.361145, -71.057083)
            boston
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 13f))

        val cameraPosition = CameraPosition.Builder()
                .target(location)      // Sets the center of the map to location user
                .zoom(17f)                   // Sets the zoom
                //.bearing(90f)                // Sets the orientation of the camera to east
                //.tilt(40f)                   // Sets the tilt of the camera to 30 degrees
                .build()                   // Creates a CameraPosition from the builder
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        val marker = mMap.addMarker(MarkerOptions().position(location!!).title("Marker in Sydney"))

        mMap.setOnCameraMoveListener {
            location = mMap.cameraPosition.target
            marker.position = location
            println(location)
        }
    }

    companion object {
        const val REQUEST_CODE = 4
        const val PERMISSION_REQUEST_CODE = 5
        const val LOCATION_EXTRA = "driver.itemp2p.com.driver.ChooseLocationActivity_locationExtra"

        fun start(from: Activity,identifier: Int) {
            from.startActivityForResult<ChooseLocationActivity>(REQUEST_CODE+identifier)
        }
    }
}
