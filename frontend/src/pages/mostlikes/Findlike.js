import React, {useEffect,useState} from "react";
import axios from "axios";
import "./likes.css";
import Navbarr from "../Navbar/Indexx";
import  { Fragment } from 'react'




function Findlike() {
    const [guides, setGuides]= useState(null);
    const url='http://localhost:8080/findlikes/'
    

    useEffect(()=>{
        const token= 'Bearer '+ localStorage.getItem('token');
        axios.get(url,{
        headers: {
             'Authorization': token
            }
          },[url]).
         
        then(Response =>{
          setGuides(Response.data)
        })
       },[url])
       
    


       


return(
   


       
   
    
    
    <div className = "container"> 

    <Navbarr/>
    <h1>Most Popular guides</h1>   
    
   
   {guides?.map((guide, key)=>( 
    

<table>
    
    <tbody>
    <thead>
        <tr>
            <th>ID</th>
            <th>Guide Title</th>
            <th>Number Of Likes</th>
        </tr>
    </thead>
    <tbody>
    <tr>
    <td>{guide.id}</td>
    <td>{guide.title}</td>
    <td>{guide.numLikes}</td>
    
   </tr>
   </tbody>
 
  </tbody>
  </table>


  

   
    ))}
    
    </div>
    

    );


    
}

   export default Findlike;