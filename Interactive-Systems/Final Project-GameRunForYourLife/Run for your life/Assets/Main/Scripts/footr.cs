using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class footr : MonoBehaviour
{
    // Start is called before the first frame update

 

        public GameObject cube1;
        public float speed;
        // Start is called before the first frame update
        void Start()
        {

        }

        // Update is called once per frame
        void Update()
        {
        transform.RotateAround(cube1.transform.position, Vector3.right, speed * Time.deltaTime);

    }

  
    }
