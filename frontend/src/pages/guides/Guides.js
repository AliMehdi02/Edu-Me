import "./GuideFeed.css";

import React, {useEffect,useState} from "react";
import { Card, Container, Button, Image } from "react-bootstrap";
import { idToExp } from "../../utils/Utils";
import {useNavigate, useParams} from 'react-router-dom';
import axios from "axios";
import Navbarr from "../Navbar/Indexx";
import MatchMe from "../userMatches/MatchMe"

function Guides() {
  const{user_id}=useParams()
  const navigate = useNavigate();
  const [guides, setGuides]= useState(null);

  const handleLogout= ()=>{
    localStorage.clear();
    localStorage.setItem('isAuthenticated', false);
    navigate('/login');
  }
  const url=`http://localhost:8080/view-guides-topics/${user_id}`

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
  

 const handleClick= (guide)=>{
   navigate('/view-guide', {state:guide});
 }

 // useEffect(()=>{
 //   const token= 'Bearer '+ localStorage.getItem('token');
    
 // fetch(`http://localhost:8080/view-guides-topics/${guide.id}`, {
 //   method: 'GET',
 //   headers: {
 //       'Authorization': token
 //     }
 //   }).
 //  then((res)=> res.json()).
  //  then((json)=> setGuides(json))
  //  .catch(err => {
  //    console.error(err)
  //  });
  //},[]);

  //const handleLogout= ()=>{
  //  localStorage.clear();
   // localStorage.setItem('isAuthenticated', false);
   // navigate('/login');
  //}

  return (
    <>
    <Navbarr/>
     {localStorage.getItem('isAuthenticated')==='true' && (
    <div>
    </div>
    )}

      <div className="MatchMe-Button">
       <MatchMe />
      </div>

    <div>
   

    <h2 className="h">
         Your Preferred Guides
      </h2>

      
      <h3 className="hx">
         Like ,View and Share
      </h3>
     
    </div>
    
    {guides?.map((guide, key)=>(
       <Card key={key} onClick={()=>handleClick(guide)} className='mb-3 GuideFeed' style={{backgroundColor:'#b9a0e0'}}>
       <Card.Body>
       <Card.Title className="t">*{guide.title}</Card.Title>
         <Card.Subtitle className="tss">
           #{idToExp(guide.expLvl)}
         </Card.Subtitle>
       </Card.Body>
     </Card>
  ))}
     

    </>
  );
}

export default Guides;