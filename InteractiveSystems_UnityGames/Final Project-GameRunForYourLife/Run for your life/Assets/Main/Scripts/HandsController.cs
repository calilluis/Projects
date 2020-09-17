using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class HandsController : MonoBehaviour
{
    private Rigidbody rb;
    public Component[] donut;
    private bool isDonut2;
    void Start()
    {
        rb = GetComponent<Rigidbody>();
    }

    // Update is called once per frame
     void Update()
     {
         
     }
    // void OnTriggerEnter(Collider other) 
    // {
        
    //     if(other.gameObject.CompareTag("Donut3")){
    //         other.gameObject.SetActive (false);
    //     }
    //     if(other.gameObject.CompareTag("Donut2")){
    //         //other.gameObject.GetComponent<Rotator>().Update();
    //         other.gameObject.GetComponent<Rotator>().enabled=true;
    //         other.gameObject.tag="Donut3";
    //     }
    //     if (other.gameObject.CompareTag ( "Donut1"))
    //     {
    //         //other.gameObject.transform.Rotate (new Vector3 (0, 0, 45) * Time.deltaTime);
    //         //other.gameObject.SetActive (false);
    //         //other.gameObject.GetComponent ().material.color = Color.blue;
    //         other.gameObject.GetComponentInChildren<MeshRenderer>().material.color=Color.blue;
    //         other.gameObject.tag="Donut2";
    //         //count = count + 1;
    //         //SetCountText ();
    //     }
        
    // }
}
