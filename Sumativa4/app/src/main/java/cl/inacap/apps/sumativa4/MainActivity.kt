package cl.inacap.apps.sumativa4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    private lateinit var greenStatusRadio: RadioButton
    private lateinit var yellowStatusRadio: RadioButton
    private lateinit var redStatusRadio: RadioButton
    private lateinit var humidifyButton: Button
    private lateinit var dehumidifyButton: Button
    private lateinit var offButton: Button

    private lateinit var mqttClient: MqttClientHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greenStatusRadio = findViewById(R.id.greenStatusRadio)
        yellowStatusRadio = findViewById(R.id.yellowStatusRadio)
        redStatusRadio = findViewById(R.id.redStatusRadio)
        val statusRadioButtonList = ArrayList<RadioButton>()
        statusRadioButtonList.add(greenStatusRadio)
        statusRadioButtonList.add(yellowStatusRadio)
        statusRadioButtonList.add(redStatusRadio)

        mqttClient = MqttClientHelper()
        mqttClient.subscribeToTopic(MqttClientHelper.SENSOR_TOPIC, statusRadioButtonList)

        humidifyButton = findViewById(R.id.humidifyButton)
        dehumidifyButton = findViewById(R.id.dehumidifyButton)
        offButton = findViewById(R.id.offButton)

        humidifyButton.setOnClickListener {
            mqttClient.publishMessage(MqttClientHelper.DEVICE_TOPIC, "HUMIDIFIER")
        }

        dehumidifyButton.setOnClickListener {
            mqttClient.publishMessage(MqttClientHelper.DEVICE_TOPIC, "DEHUMIDIFIER")
        }

        offButton.setOnClickListener {
            mqttClient.publishMessage(MqttClientHelper.DEVICE_TOPIC, "OFF")
        }
    }
}