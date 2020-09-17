using UnityEngine;
using System.Collections;

public class Rotator : MonoBehaviour {


    void Update () 
    {
        transform.Rotate (new Vector3 (45, 15, 45) * Time.deltaTime);
    }
}