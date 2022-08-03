import React, { useEffect, useState } from 'react'
import "./AllMatches.css";
import Match from "./Match";
import axios from "axios";
import NavBar from "../Navbar/Indexx";

import { useParams } from 'react-router-dom';

 function AllMatches() { 
  const [matches, setMatches]= useState([
  // {
  //     id: 0,
  //     percentage: 0,
  //     username : null,
  //     image: null
  // }
  ]);
 
    const {id} = useParams();
    const url= `http://localhost:8080/getMatch/${id}`;

  useEffect(() => {

    const token= 'Bearer ' + localStorage.getItem('token');
    axios.get(url,
      {
        headers: 
        {
          'Authorization': token
        }
      },[url] ).
     then(Response =>
      {
        setMatches(Response.data);
        console.log(matches);
      }, [])
        
    }, [])

      console.log(matches);

  

         return (

      <div className="AllMatches">
         < NavBar />
          <div className= "AllMatches-header"> 
              <h1>Matches to your preferences</h1>
          </div>
          <div className="AllMatches-cards">
          
           {matches.length === 0 ? 
              <div className='AllMatches-error'>
                <h1> No results </h1> 
                <div className='AllMatches-errorMessage'> 
                <p>Try limiting the 'topics to learn' in your profile</p> 
                </div>
                
              </div>
           : null}
      
          {matches.map((results) => (
            <Match
              id={results.id}
              match_percentage={results.percentage}
              username={results.username}
              image ={results.image}
            />
          ))}
          
      

        
          </div>

      </div>
    );
          
 }
   
 export default AllMatches;