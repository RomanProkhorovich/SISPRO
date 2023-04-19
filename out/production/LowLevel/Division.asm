_text SEGMENT
; ecx arg1, edx arg2
asm_div proc frame
	.endprolog
	mov eax, ecx
	mov ecx, edx
	xor edx, edx
	div ecx
	ret
asm_div endp

; ecx arg1, edx arg2
asm_mask proc frame

	.endprolog
	mov eax, ecx
	or eax, edx
	ret
asm_mask endp

_text ends
end