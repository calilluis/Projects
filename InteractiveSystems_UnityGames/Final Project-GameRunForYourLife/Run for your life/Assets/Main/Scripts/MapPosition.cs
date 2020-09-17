using System.Collections;
using System.Collections.Generic;
using System.Security.Cryptography;
using UnityEngine;

public class MapPosition : MonoBehaviour
{
    public GameObject TargetObject1;
    public GameObject TargetObject2;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        float x = (TargetObject1.transform.position.x+ TargetObject2.transform.position.x) / 2.0f;
        float y = (TargetObject1.transform.position.y + TargetObject2.transform.position.y) / 2.0f;
        transform.position = new Vector3(x, y-130, transform.position.z);
        
    }
}
