import React from 'react'
import { render } from '@testing-library/react'
import Home from "./Home";

export const homePageHeaderClass = "homePageHeader"

describe('Home', () => {
	it("should render an item list header", async () => {
		const {container} = render(<Home />);
		expect(container.getElementsByClassName(homePageHeaderClass).length).toBe(1);
	})
})