import React,{useEffect,useState} from 'react'
import NavBar from './NavBar'
import {useNavigate,useParams} from 'react-router-dom'
import axios from 'axios'
import { Card } from 'react-bootstrap'
import UserGuides from './UserGuides'
//import './pages/userMatches/Match.css'

function MatchingGuides() {
  const{id}=useParams()
 const[matchedGuide,setMatchedGuide]=useState([])

const url=`http://localhost:8080/view-guides-topics/${id}`




useEffect(()=>{
  const token= 'Bearer '+ localStorage.getItem('token');
  axios.get(url,{
  headers: {
       'Authorization': token
      }
    },[url]).
   
  then(Response =>{
    setMatchedGuide(Response.data)
  })
 },[url])




   return (
    <div>
      <NavBar/>
      <div className="AllMatches" >

            {matchedGuide.map((results) => ( 
             
             <UserGuides
                title={results.title}
                description={results.description}
              />
            ))}
          </div>
     
  </div>
    );
  
  
   
      
}

export default MatchingGuides