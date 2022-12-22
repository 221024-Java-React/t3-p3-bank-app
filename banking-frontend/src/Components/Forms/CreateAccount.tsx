import React from 'react'
import { axInst } from '../../Util/axInstance';

const CreateAccount = () => {

  const createAccountHandler = (e: any) => {
    e.preventDefault();
    console.log(e.target.value);
    // axInst.post("/create",
    // {
    //   params: {
    //     e.target.value,
    //   }
    // })
  }

  return (
    <form onSubmit={createAccountHandler}>
      <select name="accountType" id="accountType" >
        <option value="Checking">Checking</option>
        <option value="Savings">Savings</option>
      </select>
      <button type="submit">Create Account</button>
    </form>
  )
}

export default CreateAccount