import React, {useEffect,useState} from "react";
import { Card, Container, Button, Image } from "react-bootstrap";
import { idToExp } from "../../utils/Utils";
import {useNavigate} from 'react-router-dom';

function Guides() {
  const navigate = useNavigate();
  const [guides, setGuides]= useState([]);

  const handleClick= (guide)=>{
    navigate('/view-guide', {state:guide});
  }

  useEffect(()=>{
    const token= 'Bearer '+ localStorage.getItem('token');
    
  fetch("http://localhost:8080/view-guides", {
    method: 'GET',
    headers: {
        'Authorization': token
      }
    }).
    then((res)=> res.json()).
    then((json)=> setGuides(json))
    .catch(err => {
      console.error(err)
    });
  },[]);

  const handleLogout= ()=>{
    localStorage.clear();
    localStorage.setItem('isAuthenticated', false);
    navigate('/login');
  }

  return (
    <>
     {localStorage.getItem('isAuthenticated')==='true' && (
    <div className='d-flex justify-content-between mb-2'>
      <Image src='/user.png' height={30} />
    <Button variant='secondary' onClick={handleLogout}> Logout </Button></div>
    )}

    <div className='d-flex justify-content-end mb-3'>
      <Button variant='primary' onClick={()=>navigate('/create-new-guide')}> Add guide </Button>
    </div>
    
    <div className='text-center mb-3'>
      <h2>All Guides</h2>
    </div>
    
    {guides?.map((guide, key)=>(
       <Card key={key} onClick={()=>handleClick(guide)} className='mb-3' style={{backgroundColor:'#EEEEEE'}}>
       <Card.Body>
         <Card.Title>{guide.guide.title}</Card.Title>
         <Card.Subtitle className="m-2  text-muted">
           {idToExp(guide.guide.expLvl)}
         </Card.Subtitle>
       </Card.Body>
     </Card>
  ))}
     

    </>
  );
}

export default Guides;
