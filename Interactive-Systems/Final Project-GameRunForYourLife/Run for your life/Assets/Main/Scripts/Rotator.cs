using UnityEngine;
using System.Collections;

public class Rotator : MonoBehaviour {

    public int xvalue;
    public int yvalue;
    public int zvalue;

    void Update () 
    {
        transform.Rotate (new Vector3 (xvalue, yvalue, zvalue) * Time.deltaTime);
    }
}